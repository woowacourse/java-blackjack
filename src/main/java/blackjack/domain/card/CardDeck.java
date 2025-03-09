package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private Queue<Card> cards;

    public CardDeck() {
        List<Card> shuffledCards = makeShuffledCards();
        this.cards = new ArrayDeque<>();
        this.cards.addAll(shuffledCards);
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> drawCard(int count) {
        // TODO 카드 부족 해결하기 (예외, 규칙으로 사람 수 제한)
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawnCards.add(cards.poll());
        }
        return drawnCards;
    }

    private List<Card> makeShuffledCards() {
        List<Card> newCards = new ArrayList<>();
        for (CardShape shape : CardShape.values()) {
            for (CardValue value : CardValue.values()) {
                newCards.add(new Card(shape, value));
            }
        }
        Collections.shuffle(newCards);
        return newCards;
    }
}
