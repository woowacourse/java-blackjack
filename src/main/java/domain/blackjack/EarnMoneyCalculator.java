package domain.blackjack;

import static domain.blackjack.GameResult.TIE;
import static domain.blackjack.GameResult.WIN;
import static domain.blackjack.GameResult.WIN_BLACK_JACK;

class EarnMoneyCalculator {

    static int calculateEarnMoney(int bettingMoney, GameResult gameResult) {
        if (gameResult == WIN) {
            return bettingMoney;
        }
        if (gameResult == WIN_BLACK_JACK) {
            return (int) (bettingMoney * 1.5);
        }
        if (gameResult == TIE) {
            return 0;
        }
        return -bettingMoney;
    }
}
