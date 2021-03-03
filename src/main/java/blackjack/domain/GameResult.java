package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameResult {
    WIN, LOSE, DRAW;

    public static GameResult get(Player player, int dealerScore) {
        int playerScore = player.sumTotalScore();

        if (playerScore < dealerScore || playerScore > Dealer.SCORE_LIMIT) {
            return GameResult.LOSE;
        }

        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }

        return GameResult.DRAW;
    }
}
