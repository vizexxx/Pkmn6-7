package v.melnikova.pkmn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import v.melnikova.pkmn.models.Student;
import v.melnikova.pkmn.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService userService;

    @GetMapping("/all")
    public List<Student> getStudents()
    {
        return userService.getAllStudents();
    }

    @GetMapping("/group/{group}")
    public List<Student> getStudentsByGroup(@PathVariable String group)
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
