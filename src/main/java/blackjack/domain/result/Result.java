package blackjack.domain.result;

import java.util.Arrays;

public enum Result {

    // 21은 Bust 발생 기준점 입니다.
    LOSE("패", (userScore, dealerScore) -> userScore > 21 || (dealerScore <= 21 && userScore < dealerScore)),
    WIN("승", (userScore, dealerScore) -> dealerScore > 21 || userScore > dealerScore),
    DRAW("무", (userScore, dealerScore) -> userScore == dealerScore);

    private String name;
    private ResultDeterminer determiner;

    Result(String name, ResultDeterminer comparator) {
        this.name = name;
        this.determiner = comparator;
    }

    public static Result checkUserResult(int userScore, int dealerScore) {
        return Arrays.stream(values())
                .filter(result -> result.determiner.compare(userScore, dealerScore))
                .findAny()
                .orElseThrow(NullPointerException::new);
    }

    public static Result swap(Result result) {
        if (result == WIN) {
            return LOSE;
        }
        if (result == LOSE) {
            return WIN;
        }
        return result;
    }

    public String getName() {
        return name;
    }
}
