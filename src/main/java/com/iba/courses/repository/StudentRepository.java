package com.iba.courses.repository;

import com.iba.courses.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    @Query("select student from Student student")
    List<Student> getAllStudents();

}
