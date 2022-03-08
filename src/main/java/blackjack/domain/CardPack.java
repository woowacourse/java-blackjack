package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardPack {
    private final List<Card> cards = new ArrayList<>();

    public CardPack() {
        addNewCards();
        Collections.shuffle(cards);
    }

    private void addNewCards() {
        for (CardShape cardShape : CardShape.values()) {
            addCardsBy(cardShape);
        }
    }

    private void addCardsBy(CardShape cardShape) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardShape, cardNumber));
        }
    }

    public Card pickOne() {
        return cards.remove(0);
    }
}
