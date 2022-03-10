package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {

    private List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
