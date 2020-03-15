package blackjack.domain.result;

import blackjack.domain.participant.Name;

import java.util.Objects;

public class PlayerResult {
    private static final String NULL_ERR_MSG = "생성자에 Null이 들어올 수 없습니다.";
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
}
