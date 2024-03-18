package domain.blackjack;

class GameResultCalculator {

    static GameResult calculate(Player player, Dealer dealer) {
        if (player.isBlackJack() || dealer.isBlackJack()) {
            return calculateGameResultWhenSomeOneIsBlackJack(player, dealer);
        }
        if (player.isBust() || dealer.isBust()) {
            return calculateGameResultWhenSomeOneIsBust(player, dealer);
        }
        return calculateWithSummationCardPoint(player, dealer);
    }

    private static GameResult calculateGameResultWhenSomeOneIsBlackJack(Player player, Dealer dealer) {
        if (dealer.isBlackJack() && player.isBlackJack()) {
            return GameResult.TIE;
        }
        if (dealer.isBlackJack()) {
            return GameResult.LOSE;
        }
        if (player.isBlackJack()) {
            return GameResult.WIN_BLACK_JACK;
        }
        return null;
    }

    private static GameResult calculateGameResultWhenSomeOneIsBust(Player player, Dealer dealer) {
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        return null;
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
