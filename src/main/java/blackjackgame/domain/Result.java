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
        return new HashMap<>(Arrays.stream(GameOutcome.values())
                .collect(Collectors.toMap(value -> value, ignored -> 0)));
    }

    private Map<Guest, GameOutcome> generateGuestsResult(final Dealer dealer, final List<Guest> guests) {
        return guests.stream()
                .collect(Collectors.toMap(guest -> guest, guest -> judgeGuestResult(dealer, guest), (guest1,guest2) -> guest1, LinkedHashMap::new));
    }

    private GameOutcome judgeGuestResult(final Dealer dealer, final Guest guest) {
        if (isBlackJack(guest)) {
            return judgeResultWhenGuestBlackJack(dealer);
        }
        if (guest.getScore() <= BLACKJACK_MAX_SCORE) {
            return judgeResultWhenGuestUnderMaxScore(dealer, guest);
        }
        return LOSE;
    }

    private boolean isBlackJack(Player player) {
        return player.getScore() == BLACKJACK_MAX_SCORE && player.getHand().size() == 2;
    }

    private GameOutcome judgeResultWhenGuestBlackJack(Dealer dealer) {
        if (dealer.getScore() == BLACKJACK_MAX_SCORE && dealer.getHand().size() == 2) {
            return DRAW;
        }
        return BLACKJACK_WIN;
    }

    private GameOutcome judgeResultWhenGuestUnderMaxScore(Dealer dealer, Guest guest) {
        int dealerScore = dealer.getScore();
        int guestScore = guest.getScore();
        if (isBlackJack(dealer) || dealerScore <= BLACKJACK_MAX_SCORE && dealerScore > guestScore) {
            return LOSE;
        }
        if (dealerScore > BLACKJACK_MAX_SCORE || dealerScore < guestScore) {
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
        if (gameOutcome == WIN || gameOutcome == BLACKJACK_WIN) {
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
        return Collections.unmodifiableMap(guestsResult);
    }

    public Map<GameOutcome, Integer> getDealerResult() {
        return Map.copyOf(dealerResult);
    }
}
