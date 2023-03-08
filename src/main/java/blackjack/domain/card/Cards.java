package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Cards generateEmptyCards() {
        return new Cards(new ArrayList<>());
    }

    public Score calculateScoreForBlackjack() {
        Score score = calculate();

        if (containsAce()) {
            return score.plusIfSoftHand();
        }

        return score;
    }

    private Score calculate() {
        return new Score(cards.stream()
                .mapToInt(Card::convertToBlackjackScore)
                .sum());
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        Score currentScore = calculateScoreForBlackjack();
        return currentScore.isBust();
    }

    public Cards add(Card card) {
        final List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);

        return new Cards(newCards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
