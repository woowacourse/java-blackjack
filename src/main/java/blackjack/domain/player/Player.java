package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {

    private static final int NUM_CARDS_FOR_BLACKJACK = 2;

    protected final Cards cards;

    Player(final Cards cards) {
        this.cards = cards;
    }

    public abstract boolean isInPlaying();

    public abstract String getName();

    public boolean isBust() {
        return HandsState.from(cards.calculateScore()) == HandsState.BUST;
    }

    public boolean isBlackJack() {
        return HandsState.from(cards.calculateScore()) == HandsState.MAX_SCORE
                && cards.getCardsSize() == NUM_CARDS_FOR_BLACKJACK;
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
