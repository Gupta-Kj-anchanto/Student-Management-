package com.assignment.firstproject.controller;

import com.assignment.firstproject.exception.recordNotFoundException;
import com.assignment.firstproject.model.student;
import com.assignment.firstproject.service.studentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/student")
public class studentController {

    private final Logger logger = LoggerFactory.getLogger(studentController.class);


    @Autowired
    studentService service;

    @GetMapping
    ResponseEntity<List<student>> getAllStudents() {
        ResponseEntity<List<student>> response =  null;
        List<student> listStudents = service.getAllStudents();

        if(listStudents ==null)
        {
            response = new ResponseEntity<List<student>>(HttpStatus.NO_CONTENT);
        }
        else
            response = new ResponseEntity<List<student>>( listStudents, HttpStatus.OK);

        return response;

    }

    @GetMapping("/{id}")
    student getStudentByItsID(@Valid @PathVariable @Min(1) Long id) {
        try {
            student studentObject = service.findStudentById(id);
            return studentObject;
        }
        catch(Exception error){
            logger.error("Student Not Found");
            throw new recordNotFoundException("Student", id);
        }
    }


    @PostMapping
    ResponseEntity<student> createStudent(@Valid @RequestBody student newStudent) {
        ResponseEntity<student> response =  null;
        try {

             student studentObject = service.createStudent(
                    newStudent.getFirstName(), newStudent.getLastName(), newStudent.getAddress(),
                    newStudent.getDob(), newStudent.getPhoneNumber(), newStudent.getEmailId()
            );
            response = new ResponseEntity<student>(studentObject,HttpStatus.CREATED);
        }
        catch(Exception error)
        {
            logger.error("Exception: ",error.getMessage());
            response = new ResponseEntity<student>( HttpStatus.BAD_REQUEST);
        }

         return response;
    }

    //Deleting A student by its id with all its results
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteStudentByItsId(@Valid @PathVariable @Min(1) Long id)
    {
        ResponseEntity<String> response =  null;
        try {
            service.deleteStudentByItsId(id);
            response= ResponseEntity.ok().body("The student is deleted successfully");
        }
        catch(Exception error)
        {
            logger.error("Student not found Exception");
            throw new recordNotFoundException("Student",id);
        }

        return response;
    }


    //Update a student by its id
    @PutMapping("/{id}")
    ResponseEntity<student> updateStudent(@Valid @RequestBody student newStudent, @PathVariable Long id ){
        ResponseEntity<student> response =  null;
        newStudent.setId(id);
        System.out.print("id-"+ id);
        try {
            student studentObject = service.updateStudent(newStudent);
            response = new ResponseEntity<student>(studentObject,HttpStatus.OK);
        }
        catch(Exception error)
        {
            logger.error("Student not found");
            throw new recordNotFoundException("Student",id);
        }
        return response;
    }

}
