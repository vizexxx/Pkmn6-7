package v.melnikova.pkmn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import v.melnikova.pkmn.entity.StudentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentEntityRepository extends JpaRepository<StudentEntity, UUID> {
    List<StudentEntity> findAll();

    Optional<StudentEntity> findByFirstNameAndSurNameAndFamilyName(String surName, String firstName, String familyName);

    List<StudentEntity> findByGroup(String group);

    Optional<StudentEntity> findByFirstNameAndSurNameAndFamilyNameAndGroup(String surName, String firstName, String familyName, String group);
}
