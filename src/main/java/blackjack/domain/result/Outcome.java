package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Outcome {
    PLAYER_WIN("승", "패", (playerScore, dealerScore) ->
        (playerScore.isBlackJack() && !dealerScore.isBlackJack()) ||
            (!playerScore.isBust()
            && (playerScore.isMoreThanScore(dealerScore) || dealerScore.isBust()))),
    PLAYER_DRAW("무", "무", (playerScore, dealerScore) ->
        playerScore.equals(dealerScore) && !playerScore.isBust()),
    PLAYER_LOSE("패", "승", (playerScore, dealerScore) ->
        (!playerScore.isBlackJack() && dealerScore.isBlackJack()) ||
        dealerScore.isMoreThanScore(playerScore) || playerScore.isBust());

    private final String name;
    private final String converseName;
    private final BiPredicate<Score, Score> compare;

    Outcome(String name, String converseName, BiPredicate<Score, Score> compare) {
        this.name = name;
        this.converseName = converseName;
        this.compare = compare;
    }

    public static Outcome of(Score playerScore, Score dealerScore) {
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
