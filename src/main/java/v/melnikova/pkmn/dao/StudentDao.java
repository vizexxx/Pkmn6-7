package v.melnikova.pkmn.dao;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import v.melnikova.pkmn.entity.StudentEntity;
import v.melnikova.pkmn.models.Student;
import v.melnikova.pkmn.repository.StudentEntityRepository;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentDao {
    private final StudentEntityRepository studentRepository;

    @SneakyThrows
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @SneakyThrows
    public List<StudentEntity> getAllByGroup(String group) {
        return studentRepository.findByGroup(group);
    }

    @SneakyThrows
    public StudentEntity getStudentByFIO(String surName, String firstName,String familyName) {
        return studentRepository.findByFirstNameAndSurNameAndFamilyName(surName,firstName,familyName).orElseThrow(
                () -> new UserPrincipalNotFoundException("Студент не найден")
        );
    }

    @SneakyThrows
    public StudentEntity saveStudent(StudentEntity studentEntity)
    {
        return studentRepository.save(studentEntity);
    }

    public boolean searchStudent(Student student)
    {
        return studentRepository.findByFirstNameAndSurNameAndFamilyNameAndGroup(student.getSurName(), student.getFirstName(), student.getFamilyName(), student.getGroup()).isPresent();
    }
}
