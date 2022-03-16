package blackjack.model.result;

import blackjack.model.state.State;
import blackjack.model.player.Participant;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {

    WIN("승", (dealerState, playerState) ->
            dealerState.isBlackjack() && !playerState.isBlackjack() ||
                    !dealerState.isBust() && playerState.isBust() ||
                    dealerState.isBust() && playerState.isBust() ||
                    !dealerState.isBust() && dealerState.isWinBy(playerState)),

    LOSE("패", (dealerState, playerState) ->
            !dealerState.isBlackjack() && playerState.isBlackjack() ||
                    dealerState.isBust() && !playerState.isBust() ||
                    !dealerState.isBust() && !dealerState.isWinBy(playerState)),

    DRAW("무", (dealerState, playerState) ->
            dealerState.isBlackjack() && playerState.isBlackjack() ||
                    !dealerState.isBust() && dealerState.isDrawWith(playerState)),
    ;

    private final String value;
    private final BiPredicate<State, State> biPredicate;

    MatchResult(String value, BiPredicate<State, State> biPredicate) {
        this.value = value;
        this.biPredicate = biPredicate;
    }

    public static MatchResult match(Participant dealer, Participant gamer) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.biPredicate.test(dealer.getState(), gamer.getState()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 값이 없습니다."));
    }

    public String getValue() {
        return value;
    }

    public String getReverseValue() {
        if (value.equals(WIN.getValue())) {
            return LOSE.value;
        }
        if (value.equals(LOSE.getValue())) {
            return WIN.value;
        }
        return DRAW.value;
    }
}
