package ui.output;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.user.GameState;
import model.user.Participants;
import model.user.Player;

public class BetResponse {

    private final Map<String, Long> purses;

    public BetResponse(Map<String, Long> bets) {
        this.purses = bets;
    }

    public static BetResponse create(Participants participants) {
        Map<String, Long> response = new LinkedHashMap<>();
        final List<Player> players = participants.getPlayers();
        for (Player player : players) {
            final String playerName = player.getName();
            response.put(playerName, getProfit(participants, player));
        }

        return new BetResponse(response);
    }

    private static long getProfit(final Participants participants, final Player player) {
        final long betMoney = player.getBetMoney();
        final GameState gameState = player.judgeResult(participants.getDealer());
        return gameState.calculateMoney(betMoney);
    }

    public Map<String, Long> getPurses() {
        return purses;
    }
}
