package blackjack.domain.result;

import java.util.Arrays;

import static blackjack.domain.card.HoldingCards.BUST_STANDARD;

public enum Result {

    LOSE("패", (userScore, dealerScore) -> userScore > BUST_STANDARD ||
            (dealerScore <= BUST_STANDARD && userScore < dealerScore)),
    WIN("승", (userScore, dealerScore) -> dealerScore > BUST_STANDARD || userScore > dealerScore),
    DRAW("무", (userScore, dealerScore) -> userScore == dealerScore);

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
