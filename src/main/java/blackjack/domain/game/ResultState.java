package blackjack.domain.game;

import blackjack.dto.ResultParticipantDto;

import java.util.Arrays;
import java.util.function.Function;

public enum ResultState implements StateChecker {

    BLACKJACK(betting -> (int) (betting * 1.5)) {
        @Override
        public boolean isState(ResultParticipantDto player, ResultParticipantDto dealer) {
            return player.getHandSize() == BLACKJACK_CARD_COUNT
                    && player.getScore().isBlackjack()
                    && !dealer.getScore().isBlackjack();
        }
    },
    WIN(betting -> betting) {
        @Override
        public boolean isState(ResultParticipantDto player, ResultParticipantDto dealer) {
            Score playerScore = player.getScore();
            Score dealerScore = dealer.getScore();

            return playerScore.isLessThanOrEqualTo(BLACKJACK_SCORE)
                    && (dealerScore.isLessThan(playerScore) || dealerScore.isBust());
        }
    },
    TIE(betting -> 0) {
        @Override
        public boolean isState(ResultParticipantDto player, ResultParticipantDto dealer) {
            Score playerScore = player.getScore();
            Score dealerScore = dealer.getScore();

            return playerScore.isEqualTo(dealerScore)
                    || (playerScore.isBust() && dealerScore.isBust());
        }
    },
    LOSE(betting -> betting * -1) {
        @Override
        public boolean isState(ResultParticipantDto player, ResultParticipantDto dealer) {
            Score playerScore = player.getScore();
            Score dealerScore = dealer.getScore();

            return playerScore.isLessThan(dealerScore)
                    || (playerScore.isBust() && dealerScore.isLessThanOrEqualTo(BLACKJACK_SCORE));
        }
    };

    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final Score BLACKJACK_SCORE = Score.from(21);

    private final Function<Integer, Integer> moneyFunction;

    ResultState(final Function<Integer, Integer> moneyFunction) {
        this.moneyFunction = moneyFunction;
    }

    public static ResultState getState(final ResultParticipantDto player, final ResultParticipantDto dealer) {
        return Arrays.stream(ResultState.values())
                .filter(resultState -> resultState.isState(player, dealer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("승리 조건이 존재하지 않습니다."));
    }

    public int calculateProfit(int betting) {
        return moneyFunction.apply(betting);
    }
}
