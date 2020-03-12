package blackjack.domain.result;

import java.util.Objects;

public class PlayerResult {
    private static final String NULL_ERR_MSG = "생성자에 Null이 들어올 수 없습니다.";
    private String name;
    private ResultType resultType;

    public PlayerResult(String name, ResultType resultType) {
        Objects.requireNonNull(name, NULL_ERR_MSG);
        Objects.requireNonNull(resultType, NULL_ERR_MSG);
        this.name = name;
        this.resultType = resultType;
    }

    public String showGameResult() {
        return resultType.getWord();
    }

    public String getName() {
        return name;
    }

    public ResultType getResultType() {
        return resultType;
    }
}
