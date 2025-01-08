package v.melnikova.pkmn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import v.melnikova.pkmn.entity.StudentEntity;
import v.melnikova.pkmn.models.Card;
import v.melnikova.pkmn.service.CardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("")
    public List<Card> getAllCards()
    {
        return cardService.getAllCards();
    }

    @GetMapping("/{name}")
    public Card getCardByName(@RequestParam String name)
    {
        return cardService.getCardByName(name);
    }

    @GetMapping("/owner")
    public Card getCardByOwner(@RequestBody StudentEntity studentEntity)
    {
        return cardService.getCardByOwner(studentEntity);
    }

    @GetMapping("/id/{id}")
    public Card getCardById(UUID id)
    {
        return cardService.getCardById(id);
    }

    @PostMapping("")
    public Card saveCard(Card card)
    {
        return cardService.saveCard(card);
    }
}
