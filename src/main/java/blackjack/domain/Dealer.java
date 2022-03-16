package blackjack.domain;


import java.util.List;

public class Dealer extends Gamer {

    public static final int CONVERT_POSITIVE_VALUE = -1;
    private static final int DRAWABLE_LIMIT_VALUE = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer(List<Card> cards, int battingMoney) {
        super(DEALER_NAME, battingMoney, cards);
    }

    @Override
    public boolean canHit() {
        return getTotalScore() <= DRAWABLE_LIMIT_VALUE;
    }

    public boolean calculateBattingMoneyResult(Player player) {
        if (player.createResult(this) == GameResult.TIE) {
            return false;
        }
        return addMoney(player.getBattingMoney() * CONVERT_POSITIVE_VALUE);
    }
}

