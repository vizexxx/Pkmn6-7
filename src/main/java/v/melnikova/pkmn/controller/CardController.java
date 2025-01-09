package v.melnikova.pkmn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import v.melnikova.pkmn.entity.StudentEntity;
import v.melnikova.pkmn.models.Card;
import v.melnikova.pkmn.models.Student;
import v.melnikova.pkmn.service.CardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/all")
    public List<Card> getAllCards()
    {
        return cardService.getAllCards();
    }

    @GetMapping("/name/{name}")
    public Card getCardByName(@PathVariable String name)
    {
        return cardService.getCardByName(name);
    }

    @GetMapping("/owner")
    public Card getCardByPokemonOwner(@RequestBody StudentEntity studentEntity)
    {
        return cardService.getCardByPokemonOwner(studentEntity);
    }

    @GetMapping("/id/{id}")
    public Card getCardById(@PathVariable UUID id)
    {
        return cardService.getCardById(id);
    }

    @PostMapping("")
    public Card saveCard(@RequestBody Card card)
    {
        return cardService.saveCard(card);
    }
}
