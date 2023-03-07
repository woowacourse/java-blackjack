package blackjackgame.domain;

import static blackjackgame.domain.GameOutcome.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjackgame.domain.player.Guest;

public class Result {
    private final Map<Guest, GameOutcome> guestsResult;
    private final Map<GameOutcome, Integer> dealerResult;

    public Result(final Map<Guest, GameOutcome> guestsResult) {
        this.dealerResult = initializeDealer();
        this.guestsResult = guestsResult;
    }

    private Map<GameOutcome, Integer> initializeDealer() {
        Map<GameOutcome, Integer> dealerResult = new LinkedHashMap<>();
        Arrays.stream(GameOutcome.values())
            .forEach(gameOutcome -> dealerResult.put(gameOutcome, 0));
        return dealerResult;
    }

    public void generateDealer() {
        for (final GameOutcome gameOutcome : guestsResult.values()) {
            judgeDealer(gameOutcome);
        }
    }

    private void judgeDealer(final GameOutcome gameOutcome) {
        if (gameOutcome == WIN) {
            dealerResult.put(LOSE, dealerResult.get(LOSE) + 1);
        }
        if (gameOutcome == LOSE) {
            dealerResult.put(WIN, dealerResult.get(WIN) + 1);
        }
        if (gameOutcome == DRAW) {
            dealerResult.put(DRAW, dealerResult.get(DRAW) + 1);
        }
    }

    public Map<String, String> getGuests() {
        Map<String, String> result = new LinkedHashMap<>();
        for (final Guest guest : guestsResult.keySet()) {
            result.put(guest.getName(), guestsResult.get(guest).getOutcome());
        }
        return Collections.unmodifiableMap(result);
    }

    public Map<String, Integer> getDealer() {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (final GameOutcome gameOutcome : dealerResult.keySet()) {
            result.put(gameOutcome.getOutcome(), dealerResult.get(gameOutcome));
        }
        return Collections.unmodifiableMap(result);
    }
}
