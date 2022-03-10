package blackjack.domain.card;

import java.util.LinkedList;
import java.util.Queue;

public class Deck {

    public static final String ERROR_EMPTY_DECK = "[ERROR] 더이상 뽑을 카드가 없습니다.";
    private final Queue<Card> cards;

    public Deck(CardGenerator cardGenerator) {
        this.cards = new LinkedList<>(cardGenerator.generate());
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new RuntimeException(ERROR_EMPTY_DECK);
        }
        return cards.poll();
    }

    public Queue<Card> getCards() {
        return cards;
    }
}
