package blackjack.domain;


import static blackjack.domain.BettingMoney.BETTING_MONEY_INIT_VALUE;

import java.util.List;

public class Dealer extends Gamer {

    private static final int DRAWABLE_LIMIT_VALUE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, BETTING_MONEY_INIT_VALUE, cards);
    }

    @Override
    public boolean canHit() {
        return getTotalScore() <= DRAWABLE_LIMIT_VALUE;
    }

    @Override
    public boolean calculateBettingMoneyResult(Gamer player) {
        if (player.isTie(this)) {
            return false;
        }

        if (player.isBlackJack() && isBlackJack()) {
            return false;
        }
        return addMoney(reversBettingMoney((Player) player));
    }

    public int reversBettingMoney(Player player) {
        return player.reverseBeMoney();
    }
}

