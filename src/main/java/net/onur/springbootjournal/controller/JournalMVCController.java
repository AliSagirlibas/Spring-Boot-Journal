package net.onur.springbootjournal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.onur.springbootjournal.model.Journal;
import net.onur.springbootjournal.repository.JournalRepository;

@Controller
@RequestMapping("/mvc")
public class JournalMVCController {

	@Autowired
	JournalRepository journalRepository;
	
	
	@RequestMapping("/")
	public String index(Model model)
	{
		model.addAttribute("journal",journalRepository.findAll() );
		return "index";
	}
	
	@RequestMapping("/journal")
	public @ResponseBody List<Journal> getJournalList()
	{
		
		return journalRepository.findAll();
	}
}
