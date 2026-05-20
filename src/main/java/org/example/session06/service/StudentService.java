package org.example.session06.service;

import org.example.session06.entity.Student;
import org.example.session06.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy "));
    }

    @Transactional
    public Student createStudent(Student student) {
        return repository.save(student);
    }

    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);
        student.setFullName(studentDetails.getFullName());
        student.setEmail(studentDetails.getEmail());
        student.setGpa(studentDetails.getGpa());
        return repository.save(student);
    }

    @Transactional
    public Student patchStudent(Long id, Map<String, Object> updates) {
        Student student = getStudentById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "fullName" -> student.setFullName((String) value);
                case "email" -> student.setEmail((String) value);
                case "gpa" -> student.setGpa((Double) value);
            }
        });
        return repository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        repository.delete(student);
    }
}