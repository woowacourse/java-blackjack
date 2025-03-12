package domain;

import java.util.List;

public class Rule {

    public boolean isPlayerHitAllowed(List<TrumpCard> cards) {
        Score score = Score.from(cards);

        return score.isLowerThan(Score.TWENTY_ONE)
                && score != Score.BUST;
    }

    public boolean isDealerHitAllowed(List<TrumpCard> cards) {
        Score score = Score.from(cards);

        return score.isLowerThan(Score.SEVENTEEN)
                && score != Score.BUST;
    }

    public Score evaluateScore(List<TrumpCard> cards) {
        return Score.from(cards);
    }

    public GameResult evaluateGameResult(List<TrumpCard> playerCards, List<TrumpCard> dealerCards) {
        Score playerScore = Score.from(playerCards);
        Score dealerScore = Score.from(dealerCards);

        return GameResult.of(playerScore, dealerScore);
    }
}
