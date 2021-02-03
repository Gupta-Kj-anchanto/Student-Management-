package com.assignment.firstproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]+$",message = "FirstName must contain only characters")
    @NotNull(message = "First name must not be null")
    @Size(min=2, message="First Name must have atleast 2 characters")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$",message = "LastName must contain only characters")
    @NotNull(message = "Last name must not be null")
    @Size(min=2, message="First Name must have atleast 2 characters")
    private String lastName;

    @NotNull(message = "Date Of Birth must not be NULL")
    @Past(message = "The date of birth should be present in past")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dob;

    @Pattern(regexp = "^(\\d+) ?([A-Za-z](?= ))? (.*?) ([^ ]+?) ?((?<= )APT)? ?((?<= )\\d*)?$", message = "address must contain numbers or alphabets")
    private String address;

    @Digits(fraction = 0,integer = 10, message = "Phone number must contains only digits")
    @Size(min = 10 , max= 10 , message = "Phone number is not valid as it should contains 10 digits ")
    private String phoneNumber;

    @Email(message = "email is not valid")
    @NotBlank
    private String emailId;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy="student",orphanRemoval = true)

    private Set<results> results= new HashSet<>();

    public Set<results> getResults() {
        return results;
    }

    public void addResults(results result)
    {
        this.results.add(result);
    }

    public void removeResults(results result)
    {
        this.results.remove(result);
    }
    public student() {
    }

    public student(Long id, String firstName, String lastName, Date dob, String address, String phoneNumber, String emailId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }


    @JsonCreator
    public student(@JsonProperty("firstName") String firstName,@JsonProperty("lastName") String lastName, @JsonProperty("dob")Date dob,@JsonProperty("address") String address, @JsonProperty("phoneNumber")String phoneNumber,@JsonProperty("emailId") String emailId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id= id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailId='" + emailId + '\'' +
                ", results=" + results +
                '}';
    }


}
