package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHand {

    public static final int THRESHOLD = 21;

    private final List<Card> cards;

    public CardHand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public boolean lowerThanThreshold() {
        return cards.stream()
                .mapToInt(Card::getRankValue)
                .sum() > THRESHOLD;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
