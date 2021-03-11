package blackjack.domain.card;

import blackjack.domain.game.WinOrLose;
import blackjack.domain.game.WinningRule;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void add(final Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        Score score = Score.of(0);
        for (final Card card : cards) {
            score = score.addScore(card.getScore());
        }
        return decideAceScore(score);
    }

    private Score decideAceScore(final Score score) {
        if (hasAce()) {
            return score.useAceAsEleven();
        }
        return score;
    }

    public WinOrLose compareCardsScore(final Cards other) {
        return WinningRule.calculateScores(calculateScore(), other.calculateScore());
    }

    private boolean hasAce() {
        return cards.stream()
                .filter(card -> card.isAce())
                .findFirst()
                .isPresent();
    }

    public boolean isBlackJack() {
        return calculateScore().isBlackJack();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
