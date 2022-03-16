package blackjack.model.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class CardDispenser {

    private final Queue<Card> deck;

    public CardDispenser() {
        this.deck = new LinkedList<>(shuffledCards());
    }

    private List<Card> shuffledCards() {
        List<Card> cardPool = Card.createPool();
        Collections.shuffle(cardPool);
        return cardPool;
    }

    public Card issue() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("남아있는 카드가 없습니다.");
        }
        return deck.remove();
    }
}
