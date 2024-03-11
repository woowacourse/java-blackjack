package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Cards {

    private List<Card> cards;

    public Cards() {
        createCards();
    }

    public List<Card> selectRandomCards(CardSize size) {
        return Stream.generate(this::selectRandomCard).limit(size.getSize()).toList();
    }

    private void createCards() {
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

    private Card selectRandomCard() {
        Collections.shuffle(cards);
        return cards.remove(0);
    }
}
