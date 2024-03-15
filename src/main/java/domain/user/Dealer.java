package domain.user;

import domain.Deck;
import view.OutputView;

public class Dealer {
    private static final int RECEIVABLE_THRESHOLD = 16;
    private final Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    public void play(Deck deck) {
        while (isReceivable()) {
            hand.receiveCard(deck.drawCard());
            OutputView.printDealerHitMessage();
        }
    }

    private boolean isReceivable() {
        return sumCard() <= RECEIVABLE_THRESHOLD;
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public int sumCard() {
        return hand.sumCard();
    }

    public Hand getHand() {
        return hand;
    }
}
