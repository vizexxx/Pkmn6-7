package v.melnikova.pkmn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import v.melnikova.pkmn.entity.CardEntity;
import v.melnikova.pkmn.entity.StudentEntity;
import v.melnikova.pkmn.models.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardEntityRepository extends JpaRepository<CardEntity, UUID> {
    List<CardEntity> findAllCards();

    Optional<CardEntity> findByName(String name);

    Optional<CardEntity> findByID(UUID id);

    Optional<CardEntity> findByOwner(StudentEntity studentEntity);
}
