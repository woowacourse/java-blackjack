package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Outcome {
    PLAYER_WIN("승", "패", (playerScore, dealerScore) -> playerScore > dealerScore),
    PLAYER_DRAW("무", "무", (playerScore, dealerScore) -> playerScore.equals(dealerScore)
        && playerScore != 0),
    PLAYER_LOSE("패", "승", (playerScore, dealerScore) -> playerScore < dealerScore
        || (playerScore.equals(dealerScore) && playerScore == 0));

    private final String name;
    private final String converseName;
    private final BiPredicate<Integer, Integer> compare;

    Outcome(String name, String converseName, BiPredicate<Integer, Integer> compare) {
        this.name = name;
        this.converseName = converseName;
        this.compare = compare;
    }

    public static Outcome from(int playerScore, int dealerScore) {
        return Arrays.stream(values())
            .filter(outcome -> outcome.compare.test(playerScore, dealerScore))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    public String getName() {
        return name;
    }

    public String getConverseName() {
        return converseName;
    }
}
