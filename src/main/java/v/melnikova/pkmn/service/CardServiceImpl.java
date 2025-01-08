package v.melnikova.pkmn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import v.melnikova.pkmn.dao.CardDao;
import v.melnikova.pkmn.entity.CardEntity;
import v.melnikova.pkmn.entity.StudentEntity;
import v.melnikova.pkmn.models.Card;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public abstract class CardServiceImpl implements CardService {
    private final CardDao cardDao;

    @Override
    public List<Card> getAllCards()
    {
        List<CardEntity> cardEntity = cardDao.getAllCards();
        return cardEntity.stream().map(Card::fromEntity).toList();
    }

    @Override
    public Card getCardById(UUID id)
    {
        CardEntity cardEntity = cardDao.getCardById(id);
        return Card.fromEntity(cardEntity);
    }

    @Override
    public Card getCardByName(String name)
    {
        CardEntity cardEntity = cardDao.getCardByName(name);
        return Card.fromEntity(cardEntity);
    }

    @Override
    public Card getCardByOwner(StudentEntity studentEntity)
    {
        CardEntity cardEntity = cardDao.getCardByOwner(studentEntity);
        return Card.fromEntity(cardEntity);
    }

    @Override
    public Card saveCard(Card card)
    {
        if (cardDao.searchCard(card))
            throw new IllegalArgumentException("Карточка существует в базе данных");
        if (card.getPokemonOwner() == null)
            throw new IllegalArgumentException("У покемона не существует владельца");
        return Card.fromEntity(cardDao.saveCard(CardEntity.toEntity(card)));
    }
}
