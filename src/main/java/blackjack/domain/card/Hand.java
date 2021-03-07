package blackjack.domain.card;

import static blackjack.domain.card.Rank.ACE_VALUE;

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

    public int sumAceToOne() {
        return cards.stream()
                .mapToInt(Card::getRankValue)
                .sum();
    }

    public int sumAceToEleven() {
        return cards.stream()
                .mapToInt(this::getAceValue)
                .sum();
    }

    private int getAceValue(Card card) {
        if (card.isAce()) {
            return ACE_VALUE;
        }
        return card.getRankValue();
    }
}
