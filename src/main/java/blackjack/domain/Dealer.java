package blackjack.domain;

import java.util.List;

public class Dealer extends Gamer {

    private static final int DRAWABLE_LIMIT_VALUE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, new BettingMoney(), cards);
    }

    @Override
    public boolean canHit() {
        return getTotalScore() <= DRAWABLE_LIMIT_VALUE;
    }

    @Override
    public void changeByBettingMoneyResult(Gamer player) {
        if (player.isTie(this)) {
            return;
        }
        addMoney(reversBettingMoney((Player) player));
    }

    private int reversBettingMoney(Player player) {
        return player.reverseBettingMoney();
    }
}

