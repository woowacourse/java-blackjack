package blackjackgame.domain;

import static blackjackgame.domain.GameOutcome.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private static final int BLACKJACK_MAX_SCORE = 21;

    private final Map<Guest, GameOutcome> guestsResult;
    private final Map<GameOutcome, Integer> dealerResult;

    public Result(final Dealer dealer, final List<Guest> guests) {
        this.dealerResult = initDealerResult();
        this.guestsResult = generateGuestsResult(dealer, guests);
        generateDealerResult();
    }

    private Map<GameOutcome, Integer> initDealerResult() {
        Map<GameOutcome, Integer> dealerResult = new LinkedHashMap<>();
        for (final GameOutcome gameOutcome : GameOutcome.values()) {
            dealerResult.put(gameOutcome, 0);
        }
        return dealerResult;
    }

    private Map<Guest, GameOutcome> generateGuestsResult(final Dealer dealer, final List<Guest> guests) {
        Map<Guest, GameOutcome> guestsResult = new LinkedHashMap<>();
        int dealerScore = dealer.getScore();
        for (final Guest guest : guests) {
            guestsResult.put(guest, judgeGuestResult(dealerScore, guest.getScore()));
        }
        return guestsResult;
    }

    private GameOutcome judgeGuestResult(final int dealerScore, final int guestScore) {
        if (guestScore > BLACKJACK_MAX_SCORE ||
            (dealerScore <= BLACKJACK_MAX_SCORE && dealerScore > guestScore)) {
            return LOSE;
        }
        if (dealerScore > BLACKJACK_MAX_SCORE || guestScore > dealerScore) {
            return WIN;
        }
        return DRAW;
    }

    private void generateDealerResult() {
        for (final GameOutcome gameOutcome : guestsResult.values()) {
            judgeDealerResult(gameOutcome);
        }
    }

    private void judgeDealerResult(final GameOutcome gameOutcome) {
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

    public Map<String, String> getGuestsResult() {
        Map<String, String> result = new LinkedHashMap<>();
        for (final Guest guest : guestsResult.keySet()) {
            result.put(guest.getName(), guestsResult.get(guest).getOutcome());
        }
        return Collections.unmodifiableMap(result);
    }

    public Map<String, Integer> getDealerResult() {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (final GameOutcome gameOutcome : dealerResult.keySet()) {
            result.put(gameOutcome.getOutcome(), dealerResult.get(gameOutcome));
        }
        return Collections.unmodifiableMap(result);
    }
}
