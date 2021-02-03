package com.assignment.firstproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class results {

    public results() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Semester value should not be null")
    private semester semester;

    @Max(value = 100, message = "Maximum marks for english is 100")
    @Min(value = 0, message = "Minimum marks for english is 0")
    @NotNull(message = "Marks of english should not be NULL")
    private double english;

    @Max(value = 100, message = "Maximum marks for mathematics is 100")
    @Min(value = 0, message = "Minimum marks for mathematics is 0")
    @NotNull(message = "Marks of mathematics should not be empty")
    private double mathematics;

    @Max(value = 100, message = "Maximum marks for socialScience is 100")
    @Min(value = 0, message = "Minimum marks for socialScience is 0")
    @NotNull(message = "Marks of socialScience should not be empty")
    private double socialScience;

    @Max(value = 100, message = "Maximum marks for physics is 100")
    @Min(value = 0, message = "Minimum marks for physics is 0")
    @NotNull(message = "Marks of physics should not be empty")
    private double physics;

    @Max(value = 100, message = "Maximum marks for chemistry is 100")
    @Min(value = 0, message = "Minimum marks for chemistry is 0")
    @NotNull(message = "Marks of chemistry should not be empty")
    private double chemistry;

    @Max(value = 500 , message = "Maximum total marks is 500")
    @Min(value = 0, message = "Minimum total marks is 0")
    private double total;

    @Max(value = 100 , message = "Percentage should be less than 100")
    @Min(value = 0, message = "Percentage should be more than 0")
    private double percentage;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private student student;

    public student getStudent() {
        return student;
    }

    public void setStudent(student stu) {
        this.student=  stu;
    }

    @JsonCreator
    public results(@JsonProperty("semester") semester semester,@JsonProperty("english") double english,@JsonProperty("mathematics") double mathematics,@JsonProperty("socialScience") double socialScience,@JsonProperty("physics") double physics,@JsonProperty("chemistry") double chemistry, double total,double percentage) {
        this.semester = semester;
        this.english = english;
        this.mathematics = mathematics;
        this.socialScience = socialScience;
        this.physics = physics;
        this.chemistry = chemistry;
        this.total = total;
        this.percentage = percentage;
    }

    public results(Long id, semester semester,
                   double english, double mathematics, double socialScience,
                   double physics, double chemistry, double total, double percentage) {
        this.id = id;
        this.semester = semester;
        this.english = english;
        this.mathematics = mathematics;
        this.socialScience = socialScience;
        this.physics = physics;
        this.chemistry = chemistry;
        this.total = total;
        this.percentage = percentage;
    }

    public Long getId() {
        return id;
    }


    public semester getSemester() {
        return semester;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSemester(semester semester) {
        this.semester = semester;
    }

    public double getEnglish() {
        return english;
    }

    public void setEnglish(double english) {
        this.english = english;
    }

    public double getMathematics() {
        return mathematics;
    }

    public void setMathematics(double mathematics) {
        this.mathematics = mathematics;
    }

    public double getSocialScience() {
        return socialScience;
    }

    public void setSocialScience(double socialScience) {
        this.socialScience = socialScience;
    }

    public double getPhysics() {
        return physics;
    }

    public void setPhysics(double physics) {
        this.physics = physics;
    }

    public double getChemistry() {
        return chemistry;
    }

    public void setChemistry(double chemistry) {
        this.chemistry = chemistry;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "results{" +
                "id=" + id +
                ", semester=" + semester +
                ", english=" + english +
                ", mathematics=" + mathematics +
                ", socialScience=" + socialScience +
                ", physics=" + physics +
                ", chemistry=" + chemistry +
                ", total=" + total +
                ", percentage=" + percentage +

                '}';
    }

}
