package blackjack.model;

public class Referee {

    public GameResult judge(Player player, Dealer dealer) {
        Score playerScore = player.getScore();
        Score dealerScore = dealer.getScore();

        if (player.isBlackjack() && dealer.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (player.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (dealer.isBlackjack()) {
            return GameResult.LOSE;
        }

        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }

        if (playerScore.isSame(dealerScore)) {
            return GameResult.DRAW;
        }
        if (playerScore.isLess(dealerScore)) {
            return GameResult.LOSE;
        }

        return GameResult.WIN;
    }
}
