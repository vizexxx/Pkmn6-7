package v.melnikova.pkmn.service;

import v.melnikova.pkmn.models.Student;

import java.util.List;

public interface StudentService {
    Student getStudentByFIO(String surName, String firstName,String familyName);

    List<Student> getAllStudents();

    List<Student> getAllByGroup(String group);

    Student saveStudent(Student student);
}
