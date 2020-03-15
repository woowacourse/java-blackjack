package blackjack.domain.result;

import blackjack.domain.participant.Name;

import java.util.Objects;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

public class PlayerResult {
    private Name name;
    private ResultType resultType;

    public PlayerResult(Name name, ResultType resultType) {
        Objects.requireNonNull(name, NULL_ERR_MSG);
        Objects.requireNonNull(resultType, NULL_ERR_MSG);
        this.name = name;
        this.resultType = resultType;
    }

    public String resultType() {
        return resultType.getWord();
    }

    public String name() {
        return name.getName();
    }

    public boolean hasSameResult(ResultType type) {
        return this.resultType == type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerResult that = (PlayerResult) o;
        return Objects.equals(name, that.name) &&
                resultType == that.resultType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, resultType);
    }
}
