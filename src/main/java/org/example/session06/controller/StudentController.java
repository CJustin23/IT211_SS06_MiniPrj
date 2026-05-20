package org.example.session06.controller;

import org.example.session06.entity.Student;
import org.example.session06.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private Map<String, Object> successResponse(Object data, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(successResponse(students, "Lấy danh sách sinh viên thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(successResponse(student, "Lấy thông tin sinh viên thành công"));
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Student saved = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(successResponse(saved, "Thêm sinh viên thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updated = studentService.updateStudent(id, student);
        return ResponseEntity.ok(successResponse(updated, "Cập nhật sinh viên thành công"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchStudent(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Student updated = studentService.patchStudent(id, updates);
        return ResponseEntity.ok(successResponse(updated, "Cập nhật thông tin thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}