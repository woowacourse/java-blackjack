package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int oneAceScore = calculateOneAceScore();
        if (oneAceScore + 10 <= 21) {
            return oneAceScore + 10;
        }
        return oneAceScore;
    }

    private int calculateOneAceScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
