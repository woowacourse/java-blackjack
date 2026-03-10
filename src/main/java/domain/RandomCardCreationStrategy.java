package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class RandomCardCreationStrategy implements CardCreationStrategy {
    @Override
    public Deque<Card> create() {
        List<Card> cards = createAllCards();
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    private List<Card> createAllCards() {
        List<Card> cards = new ArrayList<>();
        createAllCardsPerShape(cards);
        return cards;
    }

    private void createAllCardsPerShape(List<Card> cards) {
        for (CardShape shape : CardShape.values()) {
            createSpecificShapeCards(shape, cards);
        }
    }

    private void createSpecificShapeCards(CardShape shape, List<Card> cards) {
        for (CardContents content : CardContents.values()) {
            cards.add(new Card(shape, content));
        }
    }
}
