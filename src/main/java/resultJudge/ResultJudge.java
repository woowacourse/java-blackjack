package resultJudge;

import participant.dealer.Dealer;
import participant.player.Player;

public class ResultJudge {

    private ResultJudge() {

    }

    public static GameResult judge(Player player, Dealer dealer) {
        if (player.isBust()) {
            return GameResult.PLAYER_BUST;
        }
        if (player.isBlackJack()) {
            return playerBlackJackJudge(dealer);
        }
        return judgeWithScore(player, dealer);
    }

    private static GameResult playerBlackJackJudge(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return GameResult.PLAYER_PUSH;
        }
        return GameResult.PLAYER_BLACKJACK;
    }

    private static GameResult judgeWithScore(Player player, Dealer dealer) {
        int dealerScore = dealer.getCardScore();
        int playerScore = player.getCardScore();

        if (dealerScore == playerScore) {
            return GameResult.PLAYER_PUSH;
        }
        if (!dealer.isBust() && dealerScore > playerScore) {
            return GameResult.PLAYER_LOSE;
        }
        return GameResult.PLAYER_WIN;
    }
}
