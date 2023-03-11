package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultGame {
    private final Map<Player, WinTieLose> playersResult;
    private final Dealer dealer;
    private final List<Player> players;

    public ResultGame(final Participants participants) {
        this.dealer = participants.getDealer();
        this.players = participants.getPlayers();
        this.playersResult = new HashMap<>();
    }

    public void calculateResult() {
        players.forEach(this::put);
    }

    public WinTieLose getPlayerResult(final Player player) {
        return playersResult.get(player);
    }

    public Map<Player, WinTieLose> getPlayersResult() {
        return this.playersResult;
    }

    private void put(Player player) {
        playersResult.put(player, dealer.compareScoreWith(player.getTotalScore()));
    }
}
