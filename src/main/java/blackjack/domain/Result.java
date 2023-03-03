package blackjack.domain;

public enum Result {
    WIN,
    DRAW,
    LOSE;

    public static Result calculate(Player player, Dealer dealer) {
        int playerScore = player.calculateScore();
        int dealerScore = dealer.calculateScore();
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }
}
