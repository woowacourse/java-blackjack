package blackjack.domain;

import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 남은 카드가 없습니다.");
        }
        return cards.remove(0);
    }
}
