package blackjack.domain;

import java.util.List;

public class Dealer extends Participant {
    private static final int DEALER_MIN_TOTAL = 17;

    private final String name = "딜러";
    private final HoldingCard holdingCard;

    public Dealer(List<Card> cards) {
        this.holdingCard = new HoldingCard(cards);
    }

    public Card showFirstCard() {
        return holdingCard.getFirstCard();
    }

    public boolean isFinished() {
        return holdingCard.calculateTotal() >= DEALER_MIN_TOTAL;
    }
}
