package v.melnikova.pkmn.service;

import org.springframework.stereotype.Service;
import v.melnikova.pkmn.entity.StudentEntity;
import v.melnikova.pkmn.models.Card;

import java.util.List;
import java.util.UUID;

@Service
public interface CardService {
    List<Card> getAllCards();

    Card saveCard(Card card);

    Card getCardById(UUID id);

    Card getCardByName(String name);

    Card getCardByPokemonOwner(StudentEntity studentEntity);


}
