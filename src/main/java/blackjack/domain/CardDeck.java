package blackjack.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private Queue<Card> cards;

    private CardDeck(List<Card> cards) {
        this.cards = new ArrayDeque<>();
        this.cards.addAll(cards);
    }

    public static CardDeck initialize() {
        List<Card> cards = new ArrayList<>();
        for (CardShape shape : CardShape.values()) {
            for (CardValue value : CardValue.values()) {
                cards.add(new Card(shape, value));
            }
        }
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }


    public int getSize() {
        return cards.size();
    }

    public List<Card> drawCard(int count) {
        // TODO 카두 부족 해결하기 (예외, 규칙으로 사람 수 제한)
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawnCards.add(cards.poll());
        }
        return drawnCards;
    }
}
