package blackjack.model.deck;

import blackjack.model.cards.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class DeckManager {
    private static final int INITIAL_CARD_SIZE = 2;

    private final Queue<Card> cardDeck;

    public DeckManager(List<Card> generatedCards) {
        List<Card> shuffledCards = new ArrayList<>(generatedCards);
        Collections.shuffle(shuffledCards);
        this.cardDeck = new LinkedList<>(shuffledCards);
    }

    public Card drawCard() {
        validateEmptyDeck();
        return cardDeck.poll();
    }

    public List<Card> drawCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARD_SIZE; i++) {
            cards.add(drawCard());
        }
        return cards;
    }

    private void validateEmptyDeck() {
        if (cardDeck.isEmpty()) {
            throw new NoSuchElementException("비어있는 덱에서 카드를 뽑을 수 없습니다.");
        }
    }
}
