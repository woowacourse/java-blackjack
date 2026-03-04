package model;

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
            throw new IllegalArgumentException("카드 뭉치에 더 이상 남아있는 카드가 없습니다.");
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
