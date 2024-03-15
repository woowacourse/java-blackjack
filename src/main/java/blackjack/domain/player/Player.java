package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Player {
    protected final Name name;
    protected final Cards cards;

    public abstract List<Card> getOpenCards();

    public abstract boolean isReceivable();

    public Player(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public void drawCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.toList();
    }
}
