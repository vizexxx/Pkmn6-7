package v.melnikova.pkmn.dao;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import v.melnikova.pkmn.entity.CardEntity;
import v.melnikova.pkmn.entity.StudentEntity;
import v.melnikova.pkmn.models.Card;
import v.melnikova.pkmn.repository.CardEntityRepository;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CardDao {
    private final CardEntityRepository cardRepository;

    @SneakyThrows
    public List<CardEntity> getAllCards() {
        return cardRepository.findAll();
    }

    @SneakyThrows
    public CardEntity getCardById(UUID id) {
        return cardRepository.findById(id).orElseThrow(
                () -> new UserPrincipalNotFoundException("Карта не найдена")
        );
    }

    @SneakyThrows
    public CardEntity getCardByName(String name) {
        return cardRepository.findByName(name).orElseThrow(
                () -> new UserPrincipalNotFoundException("Карта не найдена")
        );
    }

    @SneakyThrows
    public CardEntity getCardByPokemonOwner(StudentEntity studentEntity) {
        return cardRepository.findByPokemonOwner(studentEntity).orElseThrow(
                () -> new UserPrincipalNotFoundException("Карта не найдена")
        );
    }

    @SneakyThrows
    public CardEntity saveCard(CardEntity cardEntity)
    {
        return cardRepository.save(cardEntity);
    }

    public boolean searchCard(Card card)
    {
        return cardRepository.findByName(card.getName()).isPresent();
    }
}
