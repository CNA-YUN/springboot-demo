package com.example.demo.service;

import com.example.demo.converter.StudentConverter;
import com.example.demo.dao.Student;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.beans.Transient;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        return StudentConverter.convertToStudentDTO(student);
    }

    @Override
    public Long addNewStudent(StudentDTO studentDTO) {
        List<Student> studentList = studentRepository.findByEmail(studentDTO.getEmail());
        if (!CollectionUtils.isEmpty(studentList)) {
            throw new IllegalStateException(
                    "email : " + studentDTO.getEmail() + " already exists"
            );
        }
        Student student = studentRepository.save(
                StudentConverter.convertToStudent(studentDTO)
        );
        return student.getId();
    }

    @Override
    public void deleteStudentById(long id) {
        studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student whose id is " + id + " not found")
        );
        studentRepository.deleteById(id);
    }
    @Override
    @Transactional
    public StudentDTO updateStudentById(long id, String name, String email) {
        Student studentInDB =  studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student whose id is " + id + " not found")
        );
        if (StringUtils.hasText(name)&&!studentInDB.getName().equals(name)) {
            studentInDB.setName(name);
        }
        if (StringUtils.hasText(email)&&!studentInDB.getEmail().equals(email)) {
            studentInDB.setEmail(email);
        }
        Student student =  studentRepository.save(studentInDB);
        return StudentConverter.convertToStudentDTO(student);
    }
}
