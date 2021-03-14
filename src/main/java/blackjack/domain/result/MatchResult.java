package blackjack.domain.result;

import blackjack.domain.state.State;

import java.math.BigDecimal;
import java.util.Arrays;

public enum MatchResult {
    WIN(BigDecimal.ONE) {
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
    LOSE(new BigDecimal("-1")) {
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
    DRAW(BigDecimal.ZERO) {
        @Override
        boolean match(State playerState, State dealerState) {
            if (playerState.isBlackJack() && dealerState.isBlackJack()) {
                return true;
            }
            return playerState.isDraw(dealerState);
        }
    };

    private final BigDecimal finalRate;

    MatchResult(BigDecimal finalRate) {
        this.finalRate = finalRate;
    }

    abstract boolean match(State playerState, State dealerState);

    public static MatchResult getPlayerMatchResult(State playerState, State dealerState) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.match(playerState, dealerState))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public BigDecimal finalProfitByEachStatus(BigDecimal profit) {
        return this.finalRate.multiply(profit);
    }
}
