package blackjack.domain;

import java.util.List;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_MIN_TOTAL = 17;

    public Dealer(List<Card> cards) {
        super(DEALER_NAME,new HoldingCards(cards));
    }

    public Card showFirstCard() {
        return super.getHoldingCard().getFirstCard();
    }

    public boolean isFinished() {
        return super.getHoldingCard().calculateTotal() >= DEALER_MIN_TOTAL;
    }

    public GameResult judgeResult(Player player) {
        if(player.getHoldingCard().isBust()) {
            return GameResult.WIN;
        }
        if(super.getHoldingCard().isBust() && !player.getHoldingCard().isBust()) {
            return GameResult.LOSE;
        }
        if(super.getHoldingCard().calculateTotal() > player.getHoldingCard().calculateTotal()) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }
}
