package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {

    protected final Cards cards;

    Player(final Cards cards) {
        this.cards = cards;
    }

    public abstract boolean isInPlaying();

    public abstract String getName();

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public List<String> getCardNames() {
        return cards.getCardNames();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public void takeCard(final Card card) {
        cards.takeCard(card);
    }
}
