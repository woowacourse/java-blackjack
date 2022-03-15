package blackjack.domain;

import java.util.List;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_MIN_TOTAL = 17;

    public Dealer(List<Card> cards) {
        super(DEALER_NAME,new HoldingCards(cards));
    }

    public Card showFirstCard() {
        return super.getHoldingCard().pickFirstCard();
    }

    public boolean isFinished() {
        return super.getHoldingCard().calculateTotal() >= DEALER_MIN_TOTAL;
    }

    public GameResult judgeResult(Player player) {
        if(playerBust(player) || bothNotBustAndDealerTotalLarger(player) || onlyDealerBlackJack(player)) {
            return GameResult.WIN;
        }
        if(onlyDealerBust(player) || bothNotBustAndPlayerTotalLarger(player)) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    private boolean playerBust(Player player) {
        return player.getHoldingCard().isBust();
    }

    private boolean dealerBust() {
        return super.getHoldingCard().isBust();
    }

    private boolean bothNotBustAndDealerTotalLarger(Player player) {
        return super.getHoldingCard().calculateTotal() > player.getHoldingCard().calculateTotal();
    }

    private boolean onlyDealerBust(Player player) {
        return dealerBust() && !playerBust(player);
    }

    private boolean bothNotBustAndPlayerTotalLarger(Player player) {
        return super.getHoldingCard().calculateTotal() < player.getHoldingCard().calculateTotal();
    }

    private boolean onlyDealerBlackJack(Player player) {
        return super.getHoldingCard().isBlackJack() && !player.getHoldingCard().isBlackJack();
    }
}
