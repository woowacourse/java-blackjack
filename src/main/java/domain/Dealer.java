package domain;

import controller.dto.dealer.DealerHandScore;
import controller.dto.dealer.DealerHandStatus;
import view.CardName;

public class Dealer extends Player {
    private static final int THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public Dealer(final Hand hand) {
        super(hand);
    }

    public boolean isNotUpToThreshold() {
        return this.calculateResultScore() <= THRESHOLD;
    }

    public int cardDrawCount() {
        int count = 0;
        while (isNotUpToThreshold()) {
            pickOneCard();
            count++;
        }
        return count;
    }

    public DealerHandScore getCurrentDealerHandScore() {
        DealerHandStatus dealerHand = new DealerHandStatus(CardName.getHandStatusAsString(getHand()));
        return new DealerHandScore(dealerHand, calculateResultScore());
    }

}
