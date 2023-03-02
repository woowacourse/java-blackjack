package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements Person {
    private final List<Card> cards;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public boolean isHit() {
        return false;
    }

    @Override
    public int calculateScore() {
        return 0;
    }

    @Override
    public List<Card> showCards() {
        return cards;
    }
}
