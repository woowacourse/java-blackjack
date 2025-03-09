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
        validateEmptyCardDeck(count);
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

    private void validateEmptyCardDeck(int count) {
        if (cards.size() < count) {
            throw new IllegalArgumentException("카드의 수가 부족합니다.");
        }
    }
}
