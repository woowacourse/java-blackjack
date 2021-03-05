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

    public int sumAceToOne() {
        return cards.stream()
                .mapToInt(Card::getRankValue)
                .sum();
    }

    public int sumAceToEleven() {
        return cards.stream()
                .mapToInt(this::addResult)
                .sum();
    }

    private int addResult(Card card) {
        if (card.isAce()) {
            return 11;
        }
        return card.getRankValue();
    }
}
