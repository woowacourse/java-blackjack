package blackjack.domain.game;

import blackjack.dto.ResultDto;

import java.util.Arrays;
import java.util.function.Function;

public enum ResultState implements StateChecker {

    BLACKJACK(betting -> Betting.from((int) (betting * 1.5))) {
        @Override
        public boolean isState(ResultDto player, ResultDto dealer) {
            return player.getHandSize() == BLACKJACK_CARD_COUNT
                    && player.getScore().isBlackjack()
                    && !dealer.getScore().isBlackjack();
        }
    },
    WIN(Betting::from) {
        @Override
        public boolean isState(ResultDto player, ResultDto dealer) {
            Score playerScore = player.getScore();
            Score dealerScore = dealer.getScore();

            return playerScore.isLessThanOrEqualTo(BLACKJACK_SCORE)
                    && (dealerScore.isLessThan(playerScore) || dealerScore.isBust());
        }
    },
    TIE(betting -> Betting.from(0)) {
        @Override
        public boolean isState(ResultDto player, ResultDto dealer) {
            Score playerScore = player.getScore();
            Score dealerScore = dealer.getScore();

            return playerScore.isEqualTo(dealerScore)
                    || (playerScore.isBust() && dealerScore.isBust());
        }
    },
    LOSE(betting -> Betting.from(betting * -1)) {
        @Override
        public boolean isState(ResultDto player, ResultDto dealer) {
            Score playerScore = player.getScore();
            Score dealerScore = dealer.getScore();

            return playerScore.isLessThan(dealerScore)
                    || (playerScore.isBust() && dealerScore.isLessThanOrEqualTo(BLACKJACK_SCORE));
        }
    };

    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final Score BLACKJACK_SCORE = Score.from(21);

    private final Function<Integer, Betting> moneyFunction;

    ResultState(final Function<Integer, Betting> moneyFunction) {
        this.moneyFunction = moneyFunction;
    }

    public static ResultState getState(final ResultDto player, final ResultDto dealer) {
        return Arrays.stream(ResultState.values())
                .filter(resultState -> resultState.isState(player, dealer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("승부 결과가 존재하지 않습니다."));
    }

    public Betting calculateProfit(int betting) {
        return moneyFunction.apply(betting);
    }
}
