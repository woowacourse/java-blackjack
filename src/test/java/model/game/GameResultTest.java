package model.game;

import static org.assertj.core.api.Assertions.assertThat;

import model.participant.BettingAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {
    private static final double BLACKJACK_PROFIT_RATE = 1.5;
    private static final double WIN_PROFIT_RATE = 1.0;
    private static final double PUSH_PROFIT_RATE = 0.0;
    private static final double LOSE_PROFIT_RATE = -1.0;

    private BettingAmount bettingAmount;

    @BeforeEach
    void setUp() {
        bettingAmount = BettingAmount.from(10000);
    }

    @DisplayName("블랙잭으로 승리하면 1.5배의 수익을 얻는다.")
    @Test
    void 블랙잭으로_승리할_때의_수익_계산_테스트() {
        GameResult blackjack = GameResult.BLACKJACK;
        long profit = blackjack.getProfitFrom(bettingAmount);

        long expectedProfit = Math.round(bettingAmount.getAmount() * BLACKJACK_PROFIT_RATE);
        assertThat(profit).isEqualTo(expectedProfit);
    }

    @DisplayName("블랙잭이 아닌데 승리하면 1.0배의 수익을 얻는다.")
    @Test
    void 블랙잭이_아닌데_승리할_때의_수익_계산_테스트() {
        GameResult win = GameResult.WIN;
        long profit = win.getProfitFrom(bettingAmount);

        long expectedProfit = Math.round(bettingAmount.getAmount() * WIN_PROFIT_RATE);
        assertThat(profit).isEqualTo(expectedProfit);
    }

    @DisplayName("무승부라면 베팅 금액을 돌려 받는다")
    @Test
    void 무승부라면_베팅_금액을_돌려_받는다() {
        GameResult push = GameResult.PUSH;
        long profit = push.getProfitFrom(bettingAmount);

        long expectedProfit = Math.round(bettingAmount.getAmount() * PUSH_PROFIT_RATE);
        assertThat(profit).isEqualTo(expectedProfit);
    }

    @DisplayName("패배한다면 베팅 금액을 잃는다")
    @Test
    void 패배한다면_베팅_금액을_잃는다() {
        GameResult push = GameResult.LOSE;
        long profit = push.getProfitFrom(bettingAmount);

        long expectedProfit = Math.round(bettingAmount.getAmount() * LOSE_PROFIT_RATE);
        assertThat(profit).isEqualTo(expectedProfit);
    }
}
