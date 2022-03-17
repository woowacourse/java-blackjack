package blackjack.domain.result;

import java.util.Arrays;

public enum Result {

    LOSE("패", (userScore, dealerScore) -> userScore > Constants.BUST_STANDARD ||
            (dealerScore <= Constants.BUST_STANDARD && userScore < dealerScore)),
    WIN("승", (userScore, dealerScore) -> dealerScore > Constants.BUST_STANDARD || userScore > dealerScore),
    DRAW("무", (userScore, dealerScore) -> userScore == dealerScore);

    private static class Constants {
        private static final int BUST_STANDARD = 21;
    }

    private final String name;
    private final ResultDeterminer determiner;

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

    public String getName() {
        return name;
    }
}
