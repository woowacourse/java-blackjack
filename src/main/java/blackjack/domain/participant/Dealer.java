package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.card.TrumpCard;
import blackjack.dto.Profits;

public class Dealer extends Gamer {

    public static final int DEALER_BOUND = 16;

    private final Deck deck;

    public Dealer(final Deck deck) {
        super();

        this.deck = deck;
        initialDraw();
    }

    private void initialDraw() {
        for (int i = 0; i < 2; i++) {
            hand.add(draw());
        }
    }

    public TrumpCard draw() {
        return deck.draw();
    }

    public void drawExtraCard() {
        if (canReceiveCard()) {
            hand.add(draw());
        }
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() <= DEALER_BOUND;
    }

    public TrumpCard showFirstCard() {
        return hand.getFirstCard();
    }

    public boolean isSameScore(final long score) {
        return hand.calculateScore() == score;
    }

    public int calculateProfit(final Profits profits) {
        return profits.sumProfits() * -1;
    }
}
