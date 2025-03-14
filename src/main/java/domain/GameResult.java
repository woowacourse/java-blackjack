package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {
    WIN("승", 1.0,
            (playerScore, dealerScore) -> playerScore.isHigherThan(dealerScore) && playerScore != Score.BLACKJACK),
    BLACKJACK_WIN("승 (블랙잭)", 1.5,
            (playerScore, dealerScore) -> playerScore == Score.BLACKJACK && dealerScore != Score.BLACKJACK),
    LOSE("패", -1.0,
            (playerScore, dealerScore) -> playerScore.isLowerThan(dealerScore) || playerScore == Score.BUST),
    DRAW("무", 0.0,
            (playerScore, dealerScore) -> playerScore == dealerScore && playerScore != Score.BUST);

    private final String title;
    private final double multiple;
    private final BiPredicate<Score, Score> condition;

    GameResult(String title, double multiple, BiPredicate<Score, Score> condition) {
        this.title = title;
        this.multiple = multiple;
        this.condition = condition;
    }

    public static GameResult of(Score playerScore, Score dealerScore) {
        return Arrays.stream(GameResult.values())
                .filter(result -> result.condition.test(playerScore, dealerScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 결과입니다."));
    }

    public double getMultiple() {
        return multiple;
    }
}
