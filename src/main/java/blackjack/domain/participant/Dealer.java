package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.card.TrumpCard;

import java.util.stream.IntStream;

public class Dealer extends Gamer {

    public static final int DEALER_BOUND = 16;

    private final Deck deck;

    public Dealer(final Deck deck) {
        super();

        this.deck = deck;
        initialDraw();
    }

    private void initialDraw() {
        IntStream.range(0, 2)
                .forEach(i -> hand.add(draw()));
    }

    public TrumpCard draw() {
        return deck.draw();
    }

    public boolean drawExtraCard() {
        if (canReceiveCard()) {
            hand.add(draw());
            return true;
        }

        return false;
    }

    @Override
    boolean canReceiveCard() {
        return hand.calculateScore() <= DEALER_BOUND;
    }

    public TrumpCard showFirstCard() {
        return hand.getFirstCard();
    }

    public boolean isSameScore(final long score) {
        return hand.calculateScore() == score;
    }
}
