package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CardBox {

    private final Queue<Card> cardBox;

    public CardBox() {
        List<Card> list = new ArrayList<>();
        Arrays.stream(Shape.values())
                .forEach(cardShape -> initCards(cardShape, list));
        Collections.shuffle(list);

        this.cardBox = new ArrayDeque<>(list);
    }

    private void initCards(final Shape cardShape, final List<Card> list) {
        Arrays.stream(CardInfo.values())
                .map(cardInfo -> new Card(cardShape, cardInfo))
                .forEach(list::add);
    }

    public Card get() {
        return cardBox.poll();
    }
}
