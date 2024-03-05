package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Cards {//TODO 싱글톤

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            createSameShapeCards(cardShape);
        }
    }

    private void createSameShapeCards(CardShape cardShape) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardShape, cardNumber));
        }
    }

    public List<Card> selectRandomCards(int size) {
        return Stream.generate(this::selectRandomCard).limit(size).toList();
    }

    public Card selectRandomCard() {
        Collections.shuffle(cards);
        return cards.remove(0);
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }
}
