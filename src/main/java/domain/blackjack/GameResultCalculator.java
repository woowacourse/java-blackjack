package domain.blackjack;

class GameResultCalculator {

    static GameResult calculate(Player player, Dealer dealer) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return GameResult.LOSE;
        }
        if (player.isBlackJack()) {
            return GameResult.WIN_BLACK_JACK;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        return calculateWithSummationCardPoint(player, dealer);
    }

    private static GameResult calculateWithSummationCardPoint(Player player, Dealer dealer) {
        SummationCardPoint baseSummationCardPoint = player.calculateSummationCardPoint();
        SummationCardPoint otherSummationCardPoint = dealer.calculateSummationCardPoint();
        if (baseSummationCardPoint.isBiggerThan(otherSummationCardPoint)) {
            return GameResult.WIN;
        }
        if (baseSummationCardPoint.equals(otherSummationCardPoint)) {
            return GameResult.TIE;
        }
        return GameResult.LOSE;
    }
}
