package blackjack.domain.result;

import blackjack.domain.state.State;

import java.util.Arrays;

public enum MatchResult {
    WIN {
        @Override
        boolean match(State playerState, State dealerState) {
            if (playerState.isBlackJack() && !dealerState.isBlackJack()) {
                return true;
            }
            if (playerState.isStay() && dealerState.isBust()) {
                return true;
            }
            return playerState.isStay() && playerState.isWin(dealerState);
        }
    },
    LOSE {
        @Override
        boolean match(State playerState, State dealerState) {
            if (playerState.isBust()) {
                return true;
            }
            if (!playerState.isBlackJack() && dealerState.isBlackJack()) {
                return true;
            }
            return dealerState.isStay() && dealerState.isWin(playerState);
        }
    },
    DRAW {
        @Override
        boolean match(State playerState, State dealerState) {
            if (playerState.isBlackJack() && dealerState.isBlackJack()) {
                return true;
            }
            return playerState.isDraw(dealerState);
        }
    };

    abstract boolean match(State playerState, State dealerState);

    public static MatchResult getPlayerMatchResult(State playerState, State dealerState) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.match(playerState, dealerState))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
