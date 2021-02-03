package com.assignment.firstproject.repository;

import com.assignment.firstproject.model.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface studentRepository extends JpaRepository<student, Long> {

}
