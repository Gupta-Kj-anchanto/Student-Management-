package com.assignment.firstproject.service;

import com.assignment.firstproject.exception.recordNotFoundException;
import com.assignment.firstproject.model.results;

import com.assignment.firstproject.model.semester;
import com.assignment.firstproject.model.student;
import com.assignment.firstproject.repository.resultsRepository;
import com.assignment.firstproject.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class resultsService {

    @Autowired
    EntityManager em;

    @Autowired
    resultsRepository repo;

    @Autowired
    studentRepository studentRepo;
    //getting all the results
    public List<results> getAllResults() {
        List<results> resultsList = repo.findAll();

        if (resultsList.isEmpty() == false) {
            return resultsList;
        }

        return null;
    }

    // Create results
    public results createResults(semester sem,double physics, double mathematics,double chemistry,double socialScience, double english, student stu)
    {
        double sum = physics+ mathematics+ english+chemistry+ socialScience;
        double percentage = (sum/500)*100;
        results entity =new results(sem, english ,mathematics,socialScience,physics,chemistry,sum,percentage);
        entity.setStudent(stu);
        stu.addResults(entity);

        entity = repo.save(entity);
        return entity;
    }

    //get a results by its id
    public results findResultsById(Long id)
    {
        return  repo.findById(id).orElseThrow(() -> new recordNotFoundException("Results",id));
    }

    //delete a results by its ID
    public void deleteResultsByID(Long studentId, Long id)
    {
        Optional<student> studentObject = studentRepo.findById(studentId);

        if(studentObject.isEmpty())
            throw new recordNotFoundException("Student", studentId);

        Optional<results>resultsObject = repo.findById(id);

        if(resultsObject.isPresent())
        {
            studentObject.get().removeResults(resultsObject.get());
            repo.deleteById(id);
        }
        else {
            throw new recordNotFoundException("Results",id);
        }
    }

    public results updateResult(results resultsEntity)
    {

        Optional<results> resultsObject = repo.findById(resultsEntity.getId());

        if(resultsObject.isPresent())
        {
            double sum = resultsEntity.getPhysics()+ resultsEntity.getEnglish()+ resultsEntity.getMathematics()
            + resultsEntity.getChemistry()+ resultsEntity.getSocialScience();
            double percentage = (sum/500)*100;

            resultsEntity.setTotal(sum);
            resultsEntity.setPercentage(percentage);
            resultsEntity = repo.save(resultsEntity);

        }
        else {
            throw new recordNotFoundException("Results",resultsEntity.getId());
        }

        return resultsEntity;
    }


}
