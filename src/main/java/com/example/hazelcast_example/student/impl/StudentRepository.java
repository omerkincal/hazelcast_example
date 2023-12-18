package com.example.hazelcast_example.student.impl;


import com.example.hazelcast_example.student.impl.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}