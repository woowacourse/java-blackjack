package domain;

import static view.OutputView.printBust;

public class Dealer extends Player {

    public static final String DEALER_NAME = "딜러";
    public static final int THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isBelowThreshold() {
        return getHandTotal() <= THRESHOLD;
    }

    public Card openOneCard() {
        return hand.getFirstCard();
    }

    public void drawWithThreshold(Deck deck) {
        boolean isAlive = resolveBust();
        while (isAlive) {
            if (isBelowThreshold()) {
                isAlive = drawOneMoreCard(deck);
            }
            if (isAlive && !isBelowThreshold()) {
                return;
            }
        }
        setHandTotalToZero();
        printBust();
    }

    public int getCardCount() {
        return hand.getSize();
    }

    public int getExtraSize() {
        return hand.getExtraSize();
    }

    @Override
    public boolean isNotDealer() {
        return false;
    }
}
