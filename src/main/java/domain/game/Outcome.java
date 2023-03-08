package domain.game;

import domain.player.Player;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Outcome {
    WIN, DRAW, LOSE;

    public static EnumMap<Outcome, Integer> initializeOutcomes() {
        EnumMap<Outcome, Integer> dealerOutcome = new EnumMap<>(Outcome.class);

        for (Outcome outcome : Outcome.values()) {
            dealerOutcome.put(outcome, 0);
        }

        return dealerOutcome;
    }

    public static Outcome reverseOutcome(final Outcome outcome) {
        if (outcome == Outcome.WIN) {
            return Outcome.LOSE;
        }
        if(outcome == Outcome.LOSE) {
            return Outcome.WIN;
        }
        return Outcome.DRAW;
    }

    public static Map<String, Outcome> decidePlayersOutcome(final int dealerScore, final List<Player> players) {
        Map<String, Outcome> playerOutcome = new LinkedHashMap<>();
        players.forEach((player ->
                playerOutcome.put(player.getName(), decideOutcome(dealerScore, player))
        ));

        return playerOutcome;
    }

    private static Outcome decideOutcome(final int dealerScore, final Player player) {
        if (player.isWin(dealerScore)) {
            return Outcome.WIN;
        }
        if (player.isDraw(dealerScore)) {
            return Outcome.DRAW;
        }
        return Outcome.LOSE;
    }
}
