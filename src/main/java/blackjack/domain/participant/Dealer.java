package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.card.Card;
import java.util.stream.IntStream;

public class Dealer extends Gamer {

    public static final int DEALER_BOUND = 16;

    private final Deck deck;

    public Dealer(final Deck deck) {
        super();

        this.deck = deck;
    }

    public boolean requestExtraCard() {
        if (canReceiveCard()) {
            draw(1);
            return true;
        }
        return false;
    }

    @Override
    boolean canReceiveCard() {
        return hand.calculateScore() <= DEALER_BOUND;
    }

    public void draw(final int count) {
        IntStream.range(0, count)
                .forEach(i -> hand.add(deck.draw()));
    }

    public Card draw() {
        return deck.draw();
    }

    public Card showFirstCard() {
        return hand.getFirstCard();
    }

    public boolean isSameScore(final long score) {
        return hand.calculateScore() == score;
    }
}
