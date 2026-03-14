package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class BlackjackGameReferee implements GameReferee {
    @Override
    public GameResult judge(Dealer dealer, Player player) {
        if (player.isBust()) {
            return GameResult.DEALER_WIN;
        }
        if (dealer.isBust()) {
            return GameResult.PLAYER_WIN;
        }
        return competeScoreWith(dealer, player);
    }

    private GameResult competeScoreWith(Dealer dealer, Player player) {
        Score dealerScore = dealer.getScore();
        Score playerScore = player.getScore();

        if (dealerScore.isBiggerThan(playerScore)) {
            return GameResult.DEALER_WIN;
        }
        if (playerScore.isBiggerThan(dealerScore)) {
            return GameResult.PLAYER_WIN;
        }
        return GameResult.PUSH;
    }
}
