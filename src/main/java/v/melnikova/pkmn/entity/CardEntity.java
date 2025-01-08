package v.melnikova.pkmn.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.Id;
import v.melnikova.pkmn.deserializer.SkillDeserializer;
import v.melnikova.pkmn.models.*;

import java.io.Serial;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Column(name="stage")
    @Enumerated(EnumType.STRING)
    private PokemonStage pokemonStage;

    @Column(name="name")
    private String name;

    @Column(name="hp")
    private int hp;

    @Column(name="evolves_from_id")
    private CardEntity evolvesFrom;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attack_skills")
    @JsonDeserialize(using = SkillDeserializer.class)
    private List<AttackSkill> skills;

    @Column(name="weakness_type")
    @Enumerated(EnumType.STRING)
    private EnergyType weaknessType;

    @Column(name="resistance_type")
    @Enumerated(EnumType.STRING)
    private EnergyType resistanceType;

    @Column(name="retreat_cost")
    private String retreatCost;

    @Column(name="game_set")
    private String gameSet;

    @Column(name="pokemon_type")
    @Enumerated(EnumType.STRING)
    private EnergyType pokemonType;

    @Column(name="regulation_mark")
    private char regulationMark;

    @Column(name="pokemon_owner_id")
    private StudentEntity pokemonOwner;

    @Column(name="card_number")
    private String number;

    public static CardEntity toEntity(Card card) {
        if (card == null)
            return null;
        return CardEntity.builder().id(UUID.randomUUID()).pokemonStage(card.getPokemonStage()).
                name(card.getName()).hp(card.getHp()).evolvesFrom(toEntity(card.getEvolvesFrom())).
                skills(card.getSkills()).weaknessType(card.getWeaknessType()).
                resistanceType(card.getResistanceType()).retreatCost(card.getRetreatCost()).
                gameSet(card.getGameSet()).pokemonType(card.getPokemonType()).
                regulationMark(card.getRegulationMark()).pokemonOwner(StudentEntity.toEntity(card.getPokemonOwner())).
                number(card.getNumber()).build();
    }
}
