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
        return studentRepository.getAllStudents();
    }
    public List<Student>  saveStudent(){
        Student s=new Student("Pechkin Igor","pech","asdqweasd","BSU",3);
        studentRepository.save(s);
        return studentRepository.getAllStudents();
    }
}
