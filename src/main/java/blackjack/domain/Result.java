package blackjack.domain;

import java.util.Arrays;

public enum Result {
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1);

    private final String result;
    private final int compareResult;

    Result(String result, int compareResult) {
        this.result = result;
        this.compareResult = compareResult;
    }

    public static Result decide(Dealer dealer, User user) {
        return Arrays.stream(values())
                .filter(value -> value.compareResult == user.cards.compareTo(dealer.cards))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
