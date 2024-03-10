package blackjack.model.generator;

import blackjack.model.cards.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class DeckManager {
    private final Queue<Card> cardDeck;

    public DeckManager(List<Card> generatedCards) {
        Collections.shuffle(new ArrayList<>(generatedCards));
        this.cardDeck = new LinkedList<>(generatedCards);
    }

    public Card drawCard() {
        validateEmptyDeck();
        return cardDeck.poll();
    }

    private void validateEmptyDeck() {
        if (cardDeck.isEmpty()) {
            throw new NoSuchElementException("비어있는 덱에서 카드를 뽑을 수 없습니다.");
        }
    }
}
