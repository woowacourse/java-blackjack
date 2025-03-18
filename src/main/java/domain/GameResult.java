package domain;

public enum GameResult {
    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACKJACK(0.5);

    private final double rate;

    GameResult(double rate) {
        this.rate = rate;
    }

    public static GameResult from(Player player, Dealer dealer) {
        if (hasBust(player, dealer)) {
            return getBustCaseResult(player);
        }
        if (hasBlackJack(player, dealer)) {
            return getBlackJackCaseResult(player, dealer);
        }
        return getBiggerResult(player, dealer);
    }

    public static boolean hasBust(Player player, Dealer dealer) {
        return player.isBust() || dealer.isBust();
    }

    public static GameResult getBustCaseResult(Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        return WIN;
    }

    public static boolean hasBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() || dealer.isBlackJack();
    }

    public static GameResult getBlackJackCaseResult(Player player, Dealer dealer) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return DRAW;
        }
        if (player.isBlackJack()) {
            return BLACKJACK;
        }
        return LOSE;
    }

    private static GameResult getBiggerResult(Player player, Dealer dealer) {
        if(player.getTotalScore()>dealer.getTotalScore()){
            return WIN;
        }
        if(player.getTotalScore()==dealer.getTotalScore()){
            return DRAW;
        }
        return LOSE;
    }

    public double getRate() {
        return rate;
    }
}
