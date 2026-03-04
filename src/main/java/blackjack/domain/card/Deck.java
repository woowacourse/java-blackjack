package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final String EMPTY_DECK_MESSAGE = "덱에 남은 카드가 없습니다.";

    private final List<Card> cards;

    public Deck() {
        this.cards = createAllCards();
        Collections.shuffle(this.cards);
    }

    private List<Card> createAllCards() {
        List<Card> allCards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                allCards.add(new Card(suit, rank));
            }
        }
        return allCards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(EMPTY_DECK_MESSAGE);
        }
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }
}
