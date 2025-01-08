package v.melnikova.pkmn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import v.melnikova.pkmn.models.Student;
import v.melnikova.pkmn.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class UserController {
    private final StudentService userService;

    @GetMapping("/all")
    public List<Student> getStudents()
    {
        return userService.getAllStudents();
    }

    @GetMapping("/group")
    public List<Student> getStudentsByGroup(@RequestParam String group)
    {
        return userService.getAllByGroup(group);
    }

    @GetMapping("")
    public Student getStudentByFIO(@RequestBody Student student)
    {
        return userService.getStudentByFIO(student.getSurName(),student.getFirstName(),student.getFamilyName());
    }

    @PostMapping("")
    public Student saveStudent(@RequestBody Student student)
    {
        return userService.saveStudent(student);
    }
}
