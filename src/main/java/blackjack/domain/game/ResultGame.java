package blackjack.domain.game;

import blackjack.domain.game.participantsState.OnlyDealerBustState;
import blackjack.domain.game.participantsState.PlayerState;
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
        players.forEach(player -> new PlayerState(player,dealer).calculateResult(playersResult));
    }

    public WinTieLose getPlayerResult(final Player player) {
        return playersResult.get(player);
    }

    public HashMap<Player, WinTieLose> getPlayersResult() {
        return this.playersResult;
    }
}
