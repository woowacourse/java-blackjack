package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int TOP = 0;
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (CardShape shape : CardShape.values()) {
            Arrays.stream(CardNumber.values())
                    .forEach(number -> cards.add(new Card(shape, number)));
        }
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱이 비어 있을 때 카드를 뽑을 수 없습니다.");
        }
        return cards.remove(TOP);
    }
}
