package blackjack.domain;


import java.util.List;

public class Dealer extends Gamer {

    private static final int DRAWABLE_LIMIT_VALUE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(List<Card> cards, int battingMoney) {
        super(DEALER_NAME, battingMoney, cards);
    }

    @Override
    public boolean canHit() {
        return getTotalScore() <= DRAWABLE_LIMIT_VALUE;
    }

    @Override
    public boolean calculateBattingMoneyResult(Gamer player) {
        if (player.isTie(this)) {
            return false;
        }

        if (player.isBlackJack() && isBlackJack()) {
            return false;
        }
        return addMoney(reversBattingMoney((Player) player));
    }

    public int reversBattingMoney(Player player) {
        return player.reverseBattingMoney();
    }
}

