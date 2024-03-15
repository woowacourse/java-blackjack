package domain.blackjack;

class EarnMoneyCalculator {

    static int calculateEarnMoney(int bettingMoney, GameResult gameResult) {
        return gameResult.calculateEarnMoney(bettingMoney);
    }
}
