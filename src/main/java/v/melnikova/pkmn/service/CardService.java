package v.melnikova.pkmn.service;

import v.melnikova.pkmn.entity.StudentEntity;
import v.melnikova.pkmn.models.Card;

import java.util.List;
import java.util.UUID;

public interface CardService {
    List<Card> getAllCards();

    Card saveCard(Card card);

    Card getCardById(UUID id);

    Card getCardByName(String name);

    Card getCardByOwner(StudentEntity studentEntity);
}
