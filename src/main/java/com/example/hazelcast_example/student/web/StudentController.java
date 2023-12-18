package com.example.hazelcast_example.student.web;

import com.example.hazelcast_example.student.api.StudentService;
import com.example.hazelcast_example.student.impl.Student;
import com.hazelcast.core.HazelcastInstance;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
//@CacheConfig(cacheNames  = "students")
public class StudentController {

    private final StudentService studentService;

    @Qualifier("hazelcastInstance")
    private final HazelcastInstance hazelcastInstance;

    private Map<String, Student> retrieveMap() {
        return hazelcastInstance.getMap("map");
    }

    @GetMapping("/{id}")
    //@Cacheable(key = "#id")
    public Student getStudentById(@PathVariable String id) {
        Student student = retrieveMap().get(id);
        if (student == null) {
            student = studentService.getStudentById(Integer.valueOf(id));
            if (student != null) {
                retrieveMap().put(id, student);
            }
        }
        return student;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        // Hazelcast map'ine eklenmiş öğrenci bilgisini güncelle
        if (newStudent != null) {
            retrieveMap().put(newStudent.getId().toString(), newStudent);
        }
        return newStudent;
    }

    @PutMapping("/{id}")
    //@CachePut(key = "#id")
    public Student updateStudent(@RequestBody Student student,@PathVariable String id) {
        Student newStudent = studentService.updateStudent(student, Integer.valueOf(id));
        if (newStudent != null){
            retrieveMap().put(id, newStudent);
        }
        return newStudent;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @DeleteMapping("/{id}")
    //@CacheEvict(key = "#id")
    public String deleteStudent(@PathVariable String id) {
        retrieveMap().remove(id);
        studentService.deleteStudent(Integer.valueOf(id));
        return "Silindi";
    }
}