package v.melnikova.pkmn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import v.melnikova.pkmn.dao.StudentDao;
import v.melnikova.pkmn.entity.StudentEntity;
import v.melnikova.pkmn.models.Student;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentDao studentDao;

    @Override
    public Student getStudentByFIO(String surName, String firstName,String familyName)
    {
        StudentEntity studentEntity = studentDao.getStudentByFIO(surName, firstName, familyName);
        return Student.fromEntity(studentEntity);
    }

    @Override
    public List<Student> getAllStudents()
    {
        List<StudentEntity> studentEntity = studentDao.getAllStudents();
        return studentEntity.stream().map(Student::fromEntity).toList();
    }

    @Override
    public List<Student> getAllByGroup(String group)
    {
        List<StudentEntity> studentEntity = studentDao.getAllByGroup(group);
        return studentEntity.stream().map(Student::fromEntity).toList();
    }

    @Override
    public Student saveStudent(Student student)
    {
        if (studentDao.searchStudent(student))
            throw new IllegalArgumentException("Студент существует в базе данных");
        return Student.fromEntity(studentDao.saveStudent(StudentEntity.toEntity(student)));
    }

}
