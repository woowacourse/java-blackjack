package blackjackgame.domain;

import java.util.*;
import java.util.stream.Collectors;

import static blackjackgame.domain.GameOutcome.*;

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
        return Arrays.stream(GameOutcome.values())
                .collect(Collectors.toMap(value -> value, value -> 0));
    }

    private Map<Guest, GameOutcome> generateGuestsResult(final Dealer dealer, final List<Guest> guests) {
        int dealerScore = dealer.getScore();
        return guests.stream()
                .collect(Collectors.toMap(guest -> guest, guest -> judgeGuestResult(dealerScore, guest.getScore())));
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
