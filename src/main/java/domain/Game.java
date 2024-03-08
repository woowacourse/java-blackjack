package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Game {
    //TODO Map 필드 고려
    private final Dealer dealer;
    private final Players players;

    public Game(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<Player, Result> getPlayersResult() {
        final Map<Player, Result> playerResult = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) { //TODO getPlayers() 메서드명 고려
            playerResult.put(player, player.calculateResult(dealer));
        }
        return playerResult;
    }

    public Map<Result, Integer> getDealerResult() {
        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);

        for (Result value : getPlayersResult().values()) {
            Result reversed = value.reverse();
            Integer orDefault = dealerResult.getOrDefault(reversed, 0);
            dealerResult.put(reversed, orDefault + 1);
        }
        return dealerResult;
    }
}
