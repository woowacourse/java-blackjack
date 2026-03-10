package blackjack.model;

public class Referee {

    public GameResult judge(Player player, Dealer dealer) {
        Score playerScore = player.getScore();
        Score dealerScore = dealer.getScore();

        if ((player.isBurst() && dealer.isBurst()) || (playerScore.isSame(dealerScore))) {
            return GameResult.DRAW;
        }

        if (player.isBurst() || (!dealer.isBurst() && (playerScore.isLess(dealerScore)))) {
            return GameResult.LOSE;
        }

        return GameResult.WIN;
    }
}
