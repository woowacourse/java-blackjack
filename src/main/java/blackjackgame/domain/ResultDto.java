package blackjackgame.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjackgame.domain.player.Guest;
import blackjackgame.domain.player.Name;

public class ResultDto {
    private final Map<Guest, GameOutcome> guestsResult;
    private final Map<GameOutcome, Integer> dealerResult;

    public static ResultDto from (Result result) {
        return new ResultDto(getGuests(result.getGuests()), getDealer(result.getDealer()));
    }

    public ResultDto(Map<Guest, GameOutcome> guestsResult, Map<GameOutcome, Integer> dealerResult) {
        this.guestsResult = guestsResult;
        this.dealerResult = dealerResult;
    }

    private static Map<Guest, GameOutcome> getGuests(Map<Guest, GameOutcome> guestsResult) {
        Map<Guest, GameOutcome> result = new LinkedHashMap<>();
        for (final Guest guest : guestsResult.keySet()) {
            Guest copyGuest = new Guest(new Name(guest.getName()));
            result.put(copyGuest, guestsResult.get(guest));
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

    public Map<Guest, GameOutcome> getGuestsResult() {
        return guestsResult;
    }
}
