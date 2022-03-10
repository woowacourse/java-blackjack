package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Player {
    private final String name;
    private boolean stay = false;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
        if (cards.isBust() || cards.isBlackjack()) {
            stay = true;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isAbleToHit() {
        return !stay;
    }

    public void stay() {
        stay = true;
    }

    public List<Card> getCards() {
        return cards.toList();
    }

    public int getScore() {
        return cards.calculateScore();
    }
}
