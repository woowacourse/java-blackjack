package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final String NO_REMAIN_CARD_ERROR_MESSAGE = "남은 카드가 없습니다.";
    public static final int TOP_CARD = 0;

    private final List<Card> deck;

    public Deck(List<Card> cards) {
        this.deck = new ArrayList<>(cards);
    }

    public Card drawCard() {
        if (deck.size() == 0) {
            throw new IndexOutOfBoundsException(NO_REMAIN_CARD_ERROR_MESSAGE);
        }
        return deck.remove(TOP_CARD);
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
}
