package blackjack.domian;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinnerDecider {

    private final List<Card> cards;
    private final ScoreCalculator scoreCalculator;

    public WinnerDecider(List<Card> cards, ScoreCalculator scoreCalculator) {
        this.cards = cards;
        this.scoreCalculator = scoreCalculator;
    }

    public WinningResult decide(List<Card> cards) {
        int dealerScore = scoreCalculator.calculateMaxScore(this.cards);
        int playerScore = scoreCalculator.calculateMaxScore(cards);
        if (isBlackjack(this.cards) && !isBlackjack(cards)) {
            return WinningResult.LOSE;
        }

        if (dealerScore > 21) {
            return WinningResult.WIN;
        }

        if (dealerScore == playerScore) {
            return WinningResult.DRAW;
        }
        if (dealerScore > playerScore) {
            return WinningResult.LOSE;
        }
        return WinningResult.WIN;
    }

    private boolean isBlackjack(List<Card> cards) {
        if (cards.size() != 2) {
            return false;
        }
        Set<Rank> ranks = cards.stream()
                .map(Card::getRank)
                .collect(Collectors.toSet());
        if (!ranks.contains(Rank.ACE)) {
            return false;
        }

        return ranks.contains(Rank.KING) ||
                ranks.contains(Rank.QUEEN) ||
                ranks.contains(Rank.JACK) ||
                ranks.contains(Rank.TEN);
    }
}
