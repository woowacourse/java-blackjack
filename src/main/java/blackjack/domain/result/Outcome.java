package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Outcome {
    PLAYER_WIN("승", "패", Outcome::isPlayerWin),
    PLAYER_DRAW("무", "무", Outcome::isPlayerDraw),
    PLAYER_LOSE("패", "승", Outcome::isPlayerLose);

    private static boolean isPlayerWin(Score playerScore, Score dealerScore) {
        boolean playerOnlyBlackJack = playerScore.isBlackJack() && !dealerScore.isBlackJack();
        boolean dealerOnlyBust = !playerScore.isBust() && dealerScore.isBust();
        boolean playerNotBustAndMoreThanDealer =
            !playerScore.isBust() && playerScore.isMoreThanScore(dealerScore);

        return playerOnlyBlackJack || dealerOnlyBust || playerNotBustAndMoreThanDealer;
    }

    private static boolean isPlayerDraw(Score playerScore, Score dealerScore) {
        return playerScore.equals(dealerScore) && !playerScore.isBust();
    }

    private static boolean isPlayerLose(Score playerScore, Score dealerScore) {
        boolean dealerOnlyBlackJack = !playerScore.isBlackJack() && dealerScore.isBlackJack();

        return dealerOnlyBlackJack || dealerScore.isMoreThanScore(playerScore)
            || playerScore.isBust();
    }

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

    private static boolean isCriteriaScoreOnlyBlackJack(Score criteriaScore,
        Score comparisonScore) {
        return criteriaScore.isBlackJack() && !comparisonScore.isBlackJack();
    }

    public String getName() {
        return name;
    }

    public String getConverseName() {
        return converseName;
    }
}
