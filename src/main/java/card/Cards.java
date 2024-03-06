package card;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int countAceCard() {
        return (int) cards.stream()
                .filter(card -> card.isSameCardNumber(CardNumber.ACE))
                .count();
    }
}
