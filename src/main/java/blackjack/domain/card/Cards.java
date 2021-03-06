package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collection;

import static blackjack.domain.card.painting.Symbol.ACE;

public class Cards extends ArrayList<Card> {
    private static final int BLACK_JACK = 21;
    private static final int ACE_ADDITIONAL_SCORE = 10;

    public Cards(Collection<Card> cards) {
        this.addAll(cards);
    }

    public int calculateScore() {
        int score = sumScore();

        if (hasAce() && canAddAceScore(score)) {
            score += ACE_ADDITIONAL_SCORE;
        }

        return score;
    }

    private boolean canAddAceScore(int score) {
        return score + ACE_ADDITIONAL_SCORE <= BLACK_JACK;
    }

    private int sumScore() {
        return this.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return this.stream()
                .anyMatch(symbol -> symbol.isSameSymbol(ACE));
    }
}
