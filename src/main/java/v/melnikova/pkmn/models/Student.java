package v.melnikova.pkmn.models;

import lombok.Builder;
import lombok.Data;
import v.melnikova.pkmn.entity.StudentEntity;

import java.io.Serializable;

@Data
@Builder
public class Student implements Serializable {
    private String firstName;
    private String surName;
    private String familyName;
    private String group;
    public static final long serialVersionUID = 1L;

    public Student()
    {

    }

    public Student( String surName, String firstName, String familyName, String group)
    {
        this.firstName = firstName;
        this.surName = surName;
        this.familyName = familyName;
        this.group = group;
    }

    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getSurName()
    {
        return surName;
    }
    public void setSurName(String surName)
    {
        this.surName = surName;
    }

    public String getFamilyName()
    {
        return familyName;
    }
    public void setFamilyName(String familyName)
    {
        this.familyName = familyName;
    }

    public String getGroup()
    {
        return group;
    }
    public void setGroup(String group)
    {
        this.group = group;
    }

    @Override
    public String toString()
    {
        return firstName + "/" + surName + "/" + familyName + "/" + group;
    }


    public static Student fromEntity(StudentEntity entity) {
        if (entity == null)
            return null;
        return Student.builder()
                .firstName(entity.getFirstName())
                .surName(entity.getSurName())
                .familyName(entity.getFamilyName())
                .group(entity.getGroup())
                .build();
    }


}
