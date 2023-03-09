package blackjack.domain.game;

import blackjack.domain.game.resultCalculator.ResultCalculator;
import blackjack.domain.game.resultCalculator.ResultCalculatorWithBustDealer;
import blackjack.domain.game.resultCalculator.ResultCalculatorWithBustPlayer;
import blackjack.domain.game.resultCalculator.ResultCalculatorWithNotBustDealerPlayer;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.List;

public class ResultGame {
    private final HashMap<Player, WinTieLose> playersResult;
    private final Dealer dealer;
    private final List<Player> players;

    public ResultGame(final Participants participants) {
        this.dealer = participants.getDealer();
        this.players = participants.getPlayers();
        this.playersResult = new HashMap<>();
    }

    public void calculateResult() {
        players.forEach(player -> compareScore(player).calculateResult(playersResult, player, dealer));
    }

    private ResultCalculator compareScore(final Player player) {
        if (dealer.isBust()) {
            return new ResultCalculatorWithBustDealer();
        }
        if (player.isBust()) {
            return new ResultCalculatorWithBustPlayer();
        }
        return new ResultCalculatorWithNotBustDealerPlayer();
    }

    public WinTieLose getPlayerResult(final Player player) {
        return playersResult.get(player);
    }

    public HashMap<Player, WinTieLose> getPlayersResult() {
        return this.playersResult;
    }
}
