package com.iba.courses.service;

import com.iba.courses.domain.Student;
import com.iba.courses.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student>  getAllStudents(){
        System.out.println("Service");
        Student s=new Student("Andrei Minzer","andronad","andronad","BSU",4);
        studentRepository.save(s);
        return studentRepository.getAllStudents();
    }
}
