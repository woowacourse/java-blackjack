package game;

public enum GameResult {

    WIN,
    LOSE,
    DRAW,
    BLACKJACK;

    public static GameResult of(Dealer dealer, Player player) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return DRAW;
        }
        if (player.isBlackJack()) {
            return BLACKJACK;
        }
        if (dealer.isBlackJack()) {
            return LOSE;
        }
        return bust(dealer, player);
    }

    private static GameResult bust(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return ifNotBust(dealer, player);
    }

    private static GameResult ifNotBust(Dealer dealer, Player player) {
        int totalOfPlayer = player.calculateTotalPoints();
        int totalOfDealer = dealer.calculateTotalPoints();
        if (totalOfPlayer < totalOfDealer) {
            return LOSE;
        }
        if (totalOfPlayer > totalOfDealer) {
            return WIN;
        }
        return DRAW;
    }

}
