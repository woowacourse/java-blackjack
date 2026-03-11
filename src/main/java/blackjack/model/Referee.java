package blackjack.model;

public class Referee {

    public GameResult judge(Player player, Dealer dealer) {
        Score playerScore = player.getScore();
        Score dealerScore = dealer.getScore();

        if (player.isBlackjack()) {
            return GameResult.BLACKJACK;
        }

        if ((player.isBust() && dealer.isBust()) || (playerScore.isSame(dealerScore))) {
            return GameResult.DRAW;
        }

        if (player.isBust() || (!dealer.isBust() && (playerScore.isLess(dealerScore)))) {
            return GameResult.LOSE;
        }

        return GameResult.WIN;
    }
}
