package domain.game;

import domain.card.Card;

public class Dealer extends Participant {

    public static final int DEALER_DRAW_BOUND = 16;

    private int totalProfit;

    public Dealer() {
        this.totalProfit = 0;
    }

    public void calculateDealerProfit(Chip playerBettingResult) {
        this.totalProfit -= playerBettingResult.getBettingAmount();
    }

    public boolean isUnderDrawBound() {
        return calculateTotalCardNumber() <= DEALER_DRAW_BOUND;
    }

    public Card openSingleCard() {
        return cards.getFirst();
    }

    public int getTotalProfit() {
        return totalProfit;
    }
}
