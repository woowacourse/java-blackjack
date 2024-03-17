package domain.player;

import domain.card.Card;
import domain.card.Deck;
import dto.DealerResponse;

public final class Dealer extends Participant {
    private static final int HIT_UPPER_BOUND = 17;

    private final Deck decks;

    public Dealer() {
        decks = Deck.makeDecks();
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

    public DealerResponse toDealerResponse() {
        return new DealerResponse(getHands().stream().map(Card::toCardResponse).toList(), getScore());
    }
}
