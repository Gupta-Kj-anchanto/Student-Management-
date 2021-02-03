package com.assignment.firstproject;

import com.assignment.firstproject.model.results;
import com.assignment.firstproject.model.semester;
import com.assignment.firstproject.model.student;
import com.assignment.firstproject.service.resultsService;
import com.assignment.firstproject.service.studentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;


@SpringBootApplication

public class FirstprojectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FirstprojectApplication.class, args);
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    studentService studentServices;

    @Autowired
    resultsService resultServices;


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        //Demo data
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

       logger.info("New Student is created by name Kajal ->{}",studentServices.createStudent("kajal","gupta", "61 Park Street , hello ", format.parse("23/03/1999"), "9333993333", "a1234@anchanto.com"));
       logger.info("New Student is created by name mayur ->{}",studentServices.createStudent("mayur","gupta", "61 Park Street , jala , 56lska",format.parse("23/03/1999"), "8899889900","demo@anchanto.com"));

        student stu = studentServices.findStudentById(1L);

        logger.info("New result is created by semsester ->{}",resultServices.createResults( semester.ONE, 22, 12,34,99,45,stu));
        logger.info("New result is created by semsester ->{}",resultServices.createResults( semester.TWO, 22, 32,33,56,99,stu));
        logger.info("New result is created by semsester ->{}",resultServices.createResults( semester.THREE, 22, 32,67,98,56,stu));


    }



}
