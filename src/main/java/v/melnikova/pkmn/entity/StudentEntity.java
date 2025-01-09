package v.melnikova.pkmn.entity;

import jakarta.persistence.*;
import lombok.*;
import v.melnikova.pkmn.models.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="sur_name")
    private String surName;

    @Column(name="family_name")
    private String familyName;

    @Column(name="group")
    private String group;

    public static StudentEntity toEntity(Student student)
    {
        if (student==null)
            return null;
        return StudentEntity.builder().id(UUID.randomUUID()).
                surName(student.getSurName()).firstName(student.getFirstName()).
                familyName(student.getFamilyName()).group(student.getGroup()).build();
    }
}
