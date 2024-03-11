package domain;

import controller.dto.dealer.DealerHandStatus;
import java.util.List;

public class Dealer extends Player {
    private static final int THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public Dealer(final Hand hand) {
        super(hand);
    }

    public boolean isUpToThreshold() {
        return this.calculateResultScore() > THRESHOLD;
    }

    public DealerHandStatus getDealerHandStatus() {
        return new DealerHandStatus(getDealerHandAfterStartGame());
    }

    private String getDealerHandAfterStartGame() {
        Hand hand = getHand();
        List<Card> cardsInHand = hand.getCards();
        Card firstCard = cardsInHand.get(0);
        return firstCard.getScore() + firstCard.getShape();
    }

}
