package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards = new ArrayList<>();

    public Cards() {
        createAllCards();
        Collections.shuffle(cards);
    }

    private void createAllCards() {
        for (CardShape cardShape : CardShape.values()) {
            createCardsWithShape(cardShape);
        }
    }

    private void createCardsWithShape(CardShape cardShape) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardShape, cardNumber));
        }
    }

    public Card drawOneCard() {
        return cards.remove(cards.size() - 1);
    }
}
