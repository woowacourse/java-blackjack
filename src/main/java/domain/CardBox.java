package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class CardBox {

    private Queue<Card> cardBox = new ArrayDeque<>();

    public CardBox() {
        Arrays.stream(Shape.values())
                .forEach(this::initCards);
    }

    private void initCards(final Shape cardShape) {
        List<Card> list = Arrays.stream(CardInfo.values())
                .map(cardInfo -> new Card(cardShape, cardInfo))
                .collect(Collectors.toList());
        Collections.shuffle(list);
        cardBox = new ArrayDeque<>(list);
    }

    public Card get() {
        return cardBox.poll();
    }
}
