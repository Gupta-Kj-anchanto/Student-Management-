package com.assignment.firstproject.service;

import com.assignment.firstproject.exception.recordNotFoundException;
import com.assignment.firstproject.model.student;
import com.assignment.firstproject.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class studentService {
    @Autowired
    EntityManager em;

    @Autowired
    studentRepository repo;

    //getting all the students
    public List<student> getAllStudents() {
        List<student> studentList = repo.findAll();

        if (studentList.isEmpty()==false) {
            return studentList;
        }
        return null;
    }

    //get a student by its id
    public student findStudentById(Long id)
    {
        Optional<student> studentObject = repo.findById(id);

        if(studentObject.isPresent())
        {
            return studentObject.get();
        }
        else {
            throw new recordNotFoundException("Student",id);
        }
        //return  repo.findById(id).orElseThrow(() -> new recordNotFoundException("Student",id));
    }

    // Create student
    public student createStudent(String firstName, String secondName, String Address, Date dob, String phoneNumber, String email){

        student entity =new student(firstName,secondName, dob,Address,phoneNumber,email);
        entity = repo.save(entity);
        return entity;
    }

    //delete a student by its ID
   public void deleteStudentByItsId(Long id)
   {
       Optional<student> studentObject = repo.findById(id);
       if(studentObject.isPresent())
       {
           repo.deleteById(id);
       }
       else {
           throw new recordNotFoundException("Student",id);
       }
   }

   //Updating a student
   public student updateStudent(student studentEntity)
   {
       Optional<student> studentObject = repo.findById(studentEntity.getId());

       if(studentObject.isPresent())
       {
           studentEntity = repo.save(studentEntity);
       }
       else {
           throw new recordNotFoundException("Student",studentEntity.getId());
       }

       return studentEntity;

   }


}
