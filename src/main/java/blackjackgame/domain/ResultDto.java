package blackjackgame.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjackgame.domain.player.Guest;

public class ResultDto {
    private final Map<String, GameOutcome> guestsResult;
    private final Map<GameOutcome, Integer> dealerResult;

    public ResultDto(Map<String, GameOutcome> guestsResult, Map<GameOutcome, Integer> dealerResult) {
        this.guestsResult = guestsResult;
        this.dealerResult = dealerResult;
    }

    public static ResultDto from(Result result) {
        return new ResultDto(getGuests(result.getGuests()), getDealer(result.getDealer()));
    }

    private static Map<String, GameOutcome> getGuests(Map<Guest, GameOutcome> guestsResult) {
        Map<String, GameOutcome> result = new LinkedHashMap<>();
        for (final Guest guest : guestsResult.keySet()) {
            result.put(guest.getName(), guestsResult.get(guest));
        }
        return Collections.unmodifiableMap(result);

    }

    public static Map<GameOutcome, Integer> getDealer(Map<GameOutcome, Integer> dealerResult) {
        Map<GameOutcome, Integer> result = new LinkedHashMap<>();
        for (final GameOutcome gameOutcome : dealerResult.keySet()) {
            result.put(gameOutcome, dealerResult.get(gameOutcome));
        }
        return Collections.unmodifiableMap(result);
    }

    public Map<GameOutcome, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, GameOutcome> getGuestsResult() {
        return guestsResult;
    }
}
