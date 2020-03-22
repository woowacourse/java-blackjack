package blackjack.domain.result.model;

import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.ResultType;

import java.util.Objects;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

public class WinningDto {
    private final String name;
    private final ResultType resultType;

    public WinningDto(Name name, ResultType type) {
        Objects.requireNonNull(name, NULL_ERR_MSG);
        Objects.requireNonNull(type, NULL_ERR_MSG);
        this.name = name.getName();
        this.resultType = type;
    }

    public String getName() {
        return name;
    }

    public ResultType getResultType() {
        return resultType;
    }
}
