package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Hand createEmptyHand() {
        return new Hand(new ArrayList<>());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int playerSum() {
        int result = 0;
        for (Card card : cards) {
            result += card.getRankValue();
        }
        return result;
    }

    public int dealerSum() {
        int result = 0;
        for (Card card : cards) {
            if (card.isAce()) {
                result += 11;
                continue;
            }
            result += card.getRankValue();
        }
        return result;
    }
}
