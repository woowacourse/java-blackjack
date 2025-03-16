package blackjack.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> cards;

    public CardDeck(final List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static CardDeck shuffleCardDeck() {
        List<Card> shuffledCards = Card.values();
        Collections.shuffle(shuffledCards);

        return new CardDeck(shuffledCards);
    }

    public Card pickRandomCard() {
        if(cards.isEmpty()) {
            throw new IllegalArgumentException("카드덱의 카드를 모두 소진하여 더이상 카드를 뽑을 수 없습니다.");
        }
        return cards.poll();
    }

    public Queue<Card> getCards() {
        return cards;
    }
}
