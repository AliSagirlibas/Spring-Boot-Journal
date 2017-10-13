package net.onur.springbootjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.onur.springbootjournal.model.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long>
{

	
}
