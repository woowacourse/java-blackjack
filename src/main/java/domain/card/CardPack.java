package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardPack {

    private final List<Card> cards;

    private CardPack(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card poll() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드를 모두 소진했습니다.");
        }
        return cards.removeFirst();
    }

    public static CardPack of(List<Card> cards) {
        return new CardPack(cards);
    }

    public static CardPack ofShuffled() {
        List<Card> allCards = Card.allCards();
        Collections.shuffle(allCards);
        return new CardPack(allCards);
    }
}
