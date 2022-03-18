package blackjack.domain;

import static blackjack.domain.Denomination.*;

import java.util.List;

public class Player extends Gamer {

    private static final int CONVERT_POSITIVE_VALUE = -1;

    public Player(String name, int battingMoney, List<Card> cards) {
        super(name, battingMoney, cards);
    }

    @Override
    public boolean canHit() {
        return getTotalScore() <= BLACKJACK_SCORE;
    }

    public GameResult createResult(Gamer dealer) {
        if (isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        return GameResult.of(getTotalScore(), dealer.getTotalScore(), isBlackJack());
    }

    @Override
    public boolean calculateBattingMoneyResult(Gamer dealer) {
        GameResult gameResult = createResult(dealer);

        if (dealer.isBlackJack() && isBlackJack()) {
            return true;
        }

        double result = gameResult.getBattingMoneyResult() * getBettingMoney();
        return addMoney((int) result);
    }

    public int reverseBattingMoney() {
        return getBettingMoney() * CONVERT_POSITIVE_VALUE;
    }
}
