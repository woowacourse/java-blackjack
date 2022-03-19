package blackjack.domain;

import static blackjack.domain.Denomination.*;

import java.util.List;

public class Player extends Gamer {

    private static final int CONVERT_POSITIVE_VALUE = -1;

    public Player(String name, BettingMoney battingMoney, List<Card> cards) {
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
        if (isBlackJack() && !dealer.isBlackJack()) {
            return GameResult.BLACKJACK;
        }
        return GameResult.of(getTotalScore(), dealer.getTotalScore());
    }

    @Override
    public void changeByBettingMoneyResult(Gamer dealer) {
        GameResult gameResult = createResult(dealer);
        double result = gameResult.getBettingMoneyResult() * getBettingMoney();
        addMoney((int) result);
    }

    public int reverseBettingMoney() {
        return getBettingMoney() * CONVERT_POSITIVE_VALUE;
    }
}
