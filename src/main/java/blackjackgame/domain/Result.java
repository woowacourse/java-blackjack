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

    public Map<Guest, GameOutcome> getGuestsResult() {
        return guestsResult;
    }

    public Map<GameOutcome, Integer> getDealerResult() {
        return dealerResult;
    }
}
