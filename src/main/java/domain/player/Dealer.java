package domain.player;

import domain.card.Card;
import domain.card.Deck;
import domain.state.Started;

public final class Dealer extends Participant {
    private static final int HIT_UPPER_BOUND = 17;

    private final Deck decks;

    public Dealer() {
        decks = Deck.makeDecks();
        state = Started.ofTwoCard(draw(), draw());
    }

    public Dealer(final Card first, final Card second) {
        decks = Deck.makeDecks();
        state = Started.ofTwoCard(first, second);
    }

    public Result compareHandsWith(final Player other) {
        return getState().compareWith(other.getState());
    }

    public Card draw() {
        return decks.draw();
    }

    public void automaticHit(final ActionAfterDealerHit actionAfterDealerHit) {
        while (getScore() < HIT_UPPER_BOUND) {
            hit();
            actionAfterDealerHit.run();
        }
        standIfRunning();
    }

    private void hit() {
        add(draw());
    }
}
