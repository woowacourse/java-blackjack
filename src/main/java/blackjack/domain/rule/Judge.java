package blackjack.domain.rule;

import static blackjack.domain.rule.GameResult.BLACKJACK_WIN;
import static blackjack.domain.rule.GameResult.PLAYER_LOSE;
import static blackjack.domain.rule.GameResult.PLAYER_WIN;
import static blackjack.domain.rule.GameResult.PUSH;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class Judge {

    public PlayerResults calculatePlayerResults(Dealer dealer, Players players) {
        return new PlayerResults(players.getPlayers().stream()
                .map(player -> new PlayerResult(player.getName(), calculatePlayerResult(dealer, player)))
                .toList());
    }

    public GameResult calculatePlayerResult(Dealer dealer, Player player) {
        Score playerScore = player.calculateHandScore();
        Score dealerScore = dealer.calculateHandScore();

        if (player.hasBlackJackHand() && !dealer.hasBlackJackHand()) {
            return BLACKJACK_WIN;
        }
        if (player.isBusted()) {
            return PLAYER_LOSE;
        }
        if (dealer.isBusted() || playerScore.isAbove(dealerScore)) {
            return PLAYER_WIN;
        }
        if (playerScore.equals(dealerScore)) {
            return PUSH;
        }
        return PLAYER_LOSE;
    }
}
