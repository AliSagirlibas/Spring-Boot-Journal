package net.onur.springbootjournal.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.onur.springbootjournal.model.Journal;
import net.onur.springbootjournal.repository.JournalRepository;



@RestController
@RequestMapping("/journals")
public class JournalController 
{
	
	private static final Logger logger=LoggerFactory.getLogger(JournalController.class);
	
	@Autowired
	public JournalRepository journalRepository;
		
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getJournalList()
	{
		logger.info("Get Journal List Called");
		List<Journal> journalList= journalRepository.findAll();
		if(! journalList.isEmpty())
		{
			return ResponseEntity.ok().body(journalList);
		}
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> addJournal(@RequestBody @Valid Journal journal)
	{
		logger.info("AddJournal Called");
		try
		{
			Journal j2=journalRepository.save(journal);
			logger.error(j2.getId().toString());
			URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/"+j2.getId().toString()).build().toUri();
			//return new ResponseEntity(HttpStatus.CREATED)
			return ResponseEntity.created(location).build();
		}
		catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getJournal(@PathVariable(name="id") long id) 
	{
		logger.info("GetJournal Called");
		Journal journal= journalRepository.findOne(id);
		if(journal!=null)
		{
			//return new ResponseEntity<Journal>(journal,HttpStatus.OK);
			return ResponseEntity.ok().body(journal);
		}
		return ResponseEntity.notFound().build();//  new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateJournal(@RequestBody Journal journal,@PathVariable("id") long id) 
	{
		Journal oldJournal= journalRepository.findOne(id);
		if(oldJournal==null)
		{
			logger.error("Not Found");
			return ResponseEntity.notFound().build();
		}
		else 
		{
			oldJournal.setCreated(journal.getCreated());
			oldJournal.setSummary(journal.getSummary());
			oldJournal.setTitle(journal.getTitle());
			journalRepository.save(oldJournal); 
			
			return ResponseEntity.ok().body(oldJournal);
		}
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteJournal(@PathVariable("id") long id) 
	{
		Journal oldJournal= journalRepository.findOne(id);
		if(oldJournal==null)
		{
			logger.error("Unable To delete Not Found");
			return ResponseEntity.notFound().build();
		}
		else 
		{			
			journalRepository.delete(id); 		
			return ResponseEntity.noContent().build();
		}
	}
	
	

}
