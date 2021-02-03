package com.assignment.firstproject.repository;

import com.assignment.firstproject.model.results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PreRemove;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface resultsRepository extends JpaRepository<results,Long> {

}
