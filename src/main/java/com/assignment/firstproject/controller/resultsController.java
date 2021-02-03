package com.assignment.firstproject.controller;

import com.assignment.firstproject.exception.recordNotFoundException;
import com.assignment.firstproject.model.results;
import com.assignment.firstproject.model.student;
import com.assignment.firstproject.service.resultsService;
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
@RequestMapping("/results")
public class resultsController {

    private final Logger logger = LoggerFactory.getLogger(resultsController.class);


    @Autowired
    resultsService service;

    @Autowired
    studentService studentService;


    @GetMapping
    ResponseEntity<List<results>> getAllResults() {
        ResponseEntity<List<results>> response =  null;
        try {
            List<results> listResults = service.getAllResults();

            if (listResults == null) {
                response = new ResponseEntity<List<results>>(HttpStatus.NO_CONTENT);
            } else
                response = new ResponseEntity<List<results>>(listResults, HttpStatus.OK);
        }

        catch (Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        return response;

    }

    @GetMapping("/{id}")
    results getResultsByItsID(@Valid @PathVariable @Min(1) Long id) {
        try {
            results resultsObject = service.findResultsById(id);
            return resultsObject;
        }
        catch(Exception error){
            logger.error("Results not Found");
            throw new recordNotFoundException("Results" , id);
        }

    }


    //Creating a result of a student
    @PostMapping()
    ResponseEntity<results> createResults(@Valid @RequestParam("studentId") Long studentId , @Valid @RequestBody results newResults) {


        ResponseEntity<results> response =  null;
        try {

            student studentObject = studentService.findStudentById(studentId);

            results resultsObject = service.createResults(
                    newResults.getSemester(), newResults.getPhysics(), newResults.getMathematics(),
                    newResults.getChemistry(), newResults.getSocialScience(),newResults.getEnglish(),studentObject);

            response = new ResponseEntity<results>(resultsObject,HttpStatus.CREATED);
        }
        catch(recordNotFoundException error)
        {
            logger.error("Student Not found",error.getMessage());
            throw new recordNotFoundException("Student", studentId);
        }
         return response;
    }

    //Deleting A results by its id with all its results
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteResultsByItsId(@Valid @RequestParam("studentId") Long studentId , @Valid @PathVariable @Min(1) Long id)
    {
        ResponseEntity<String> response =  null;
        try {
            service.deleteResultsByID(studentId,id);
            response= ResponseEntity.ok().body("The results is deleted successfully");
        }
        catch(Exception error)
        {
            logger.error(error.getMessage());
            throw error;
        }

        return response;
    }


    //Update a results by its id
    @PutMapping("/{id}")
    ResponseEntity<results> updateResults(@Valid @RequestBody results newResults, @PathVariable Long id ){
        ResponseEntity<results> response =  null;
        newResults.setId(id);

        try {
            results resultsObject = service.updateResult(newResults);
            response = new ResponseEntity<results>(resultsObject,HttpStatus.OK);
        }
        catch(Exception error)
        {
            logger.error("results not found");
            throw new recordNotFoundException("Results" , id);
        }
        return response;
    }

}
