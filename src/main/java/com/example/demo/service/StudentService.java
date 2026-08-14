package com.example.demo.service;

import com.example.demo.dao.Student;
import com.example.demo.dto.StudentDTO;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
   StudentDTO getStudentById(long id);
   Long addNewStudent(StudentDTO studentDTO);
   void deleteStudentById(long id);
   StudentDTO updateStudentById(long id, String name, String email);
}
