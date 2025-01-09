package v.melnikova.pkmn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import v.melnikova.pkmn.entity.CardEntity;
import v.melnikova.pkmn.entity.StudentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardEntityRepository extends JpaRepository<CardEntity, UUID> {
    List<CardEntity> findAll();

    Optional<CardEntity> findByName(String name);

    Optional<CardEntity> findById(UUID id);

    Optional<CardEntity> findByPokemonOwner(StudentEntity studentEntity);
}
