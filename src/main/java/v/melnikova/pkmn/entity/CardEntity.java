package v.melnikova.pkmn.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Column;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import v.melnikova.pkmn.deserializer.SkillDeserializer;
import v.melnikova.pkmn.models.AttackSkill;

import java.util.List;

public class CardEntity {
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attack_skills")
    @JsonDeserialize(using = SkillDeserializer.class)
    private List<AttackSkill> skills;
}
