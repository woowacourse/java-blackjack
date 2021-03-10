package blackjack.domain.result;

import blackjack.domain.state.State;

import java.util.Arrays;

public enum MatchResult {
    WIN("승") {
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
    LOSE("패") {
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
    DRAW("무") {
        @Override
        boolean match(State playerState, State dealerState) {
            if (playerState.isBlackJack() && dealerState.isBlackJack()) {
                return true;
            }
            return playerState.isDraw(dealerState);
        }
    };

    private final String result;

    MatchResult(String result) {
        this.result = result;
    }

    abstract boolean match(State playerState, State dealerState);

    public static MatchResult getPlayerMatchResult(State playerState, State dealerState) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.match(playerState, dealerState))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static MatchResult getDealerMatchResultByPlayer(MatchResult matchResult) {
        if (matchResult.equals(MatchResult.WIN)) {
            return MatchResult.LOSE;
        }
        if (matchResult.equals(MatchResult.LOSE)) {
            return MatchResult.WIN;
        }
        return MatchResult.DRAW;
    }

    public String getResult() {
        return result;
    }
}
