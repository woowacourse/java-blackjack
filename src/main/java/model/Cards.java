package model;

import constant.ErrorMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import model.dto.Card;

public class Cards {
    private final List<Card> cards = new ArrayList<>();

    public Cards() {
        initCards();
        Collections.shuffle(cards);
    }

    public Card draw() {
        if(cards.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NO_CARD_IN_DECK.getMessage());
        }
        return cards.removeFirst();
    }

    private void initCards() {
        Arrays.stream(Shape.values()).forEach(shape -> {
            Arrays.stream(CardNumber.values()).forEach(number -> {
                cards.add(new Card(shape, number));
            });
        });
    }
}
