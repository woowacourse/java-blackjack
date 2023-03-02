package blackjackgame.domain;

import static blackjackgame.domain.GameOutcome.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {
    Map<Guest, GameOutcome> guestsResult;
    Map<GameOutcome, Integer> dealerResult;

    public Result(Dealer dealer, List<Guest> guests) {
        this.dealerResult = new LinkedHashMap<>();
        for(GameOutcome gameOutcome : GameOutcome.values()) {
            dealerResult.put(gameOutcome, 0);
        }
        this.guestsResult = calculateResult(dealer, guests);
    }

    private Map<Guest, GameOutcome> calculateResult(Dealer dealer, List<Guest> guests) {
        Map<Guest, GameOutcome> guestsResult = new LinkedHashMap<>();
        int dealerScore = dealer.getScore();
        for (Guest guest : guests) {
            guestsResult.put(guest, judge(dealerScore, guest));
        }
        return guestsResult;
    }

    private GameOutcome judge(int dealerScore, Guest guest) {
        int guestScore = guest.getScore();
        if (guestScore > 21) {
            dealerResult.put(WIN, dealerResult.get(WIN) + 1);
            return LOSE;
        }
        if (dealerScore > 21) {
            dealerResult.put(LOSE, dealerResult.get(LOSE) + 1);
            return WIN;
        }
        if (guestScore > dealerScore) {
            dealerResult.put(LOSE, dealerResult.get(LOSE) + 1);
            return WIN;
        }
        if (guestScore < dealerScore) {
            dealerResult.put(WIN, dealerResult.get(WIN) + 1);
            return LOSE;
        }
        dealerResult.put(DRAW, dealerResult.get(DRAW) + 1);
        return DRAW;
    }

    public Map<String, String> getGuestsResult() {
        Map<String, String> result = new LinkedHashMap<>();
        for (Guest guest : this.guestsResult.keySet()) {
            result.put(guest.getName(), this.guestsResult.get(guest).getOutcome());
        }
        return result;
    }

    public Map<String, Integer> getDealerResult() {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (GameOutcome gameOutcome : dealerResult.keySet()) {
            result.put(gameOutcome.getOutcome(), dealerResult.get(gameOutcome));
        }
        return result;
    }
}
