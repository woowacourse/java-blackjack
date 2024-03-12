package domain;

import domain.constant.GameResult;
import domain.participant.PlayerName;

import java.util.HashMap;
import java.util.Map;

public class Betting {
    private static final Betting instance = new Betting();

    private final Map<PlayerName, Amount> betting = new HashMap<>();

    private Betting() {}

    public static Betting getInstance() {
        return instance;
    }

    public void setBetting(final PlayerName playerName, final Amount amount) {
        betting.put(playerName, amount);
    }

    public Amount getBetting(final PlayerName playerName) {
        return betting.get(playerName);
    }

    public Map<PlayerName, Integer> calculateBettingOnPlayers(final GameResults gameResults) {
        Map<PlayerName, Integer> result = new HashMap<>();
        gameResults.playerGameResults().forEach((key, value) -> result.put(key, calculateBettingOnPlayer(key, value)));
        return result;
    }

    private int calculateBettingOnPlayer(final PlayerName playerName, final GameResult gameResult) {
        if (gameResult == GameResult.WIN_BY_BLACKJACK) {
            return (int) (getBetting(playerName).getAmount() * 1.5);
        }
        if (gameResult == GameResult.WIN) {
            return getBetting(playerName).getAmount();
        }
        return getBetting(playerName).getAmount() * -1;
    }
}
