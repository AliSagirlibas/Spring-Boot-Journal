package net.onur.springbootjournal.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

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
	@Autowired
	public JournalRepository journalRepository;
		
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<Journal> getJournalList()
	{
		return journalRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> addJournal(@RequestBody @Valid Journal journal)
	{
		try
		{
			Journal j2=journalRepository.save(journal);
			URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("{/{id}").buildAndExpand(j2.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getJournal(@PathVariable(name="id") long id) 
	{
		Journal journal= journalRepository.findOne(id);
		if(journal!=null)
		{
			//return new ResponseEntity<Journal>(journal,HttpStatus.OK);
			return ResponseEntity.ok().body(journal);
		}
		return ResponseEntity.notFound().build();//  new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
	
	

}
