package model.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {
    private static final BigDecimal BLACKJACK_PROFIT_RATE = new BigDecimal("1.5");
    private static final BigDecimal WIN_PROFIT_RATE = new BigDecimal("1.0");
    private static final BigDecimal PUSH_PROFIT_RATE = new BigDecimal("0.0");
    private static final BigDecimal LOSE_PROFIT_RATE = new BigDecimal("-1.0");

    private BettingAmount bettingAmount;

    @BeforeEach
    void setUp() {
        bettingAmount = BettingAmount.from(10000);
    }

    @DisplayName("블랙잭으로 승리하면 1.5배의 수익을 얻는다.")
    @Test
    void 블랙잭으로_승리할_때의_수익_계산_테스트() {
        GameResult blackjack = GameResult.BLACKJACK;
        BigDecimal profit = blackjack.getProfitFrom(bettingAmount);

        BigDecimal expectedProfit = bettingAmount.getAmount().multiply(BLACKJACK_PROFIT_RATE);
        assertThat(profit).isEqualByComparingTo(expectedProfit);
    }

    @DisplayName("블랙잭이 아닌데 승리하면 1.0배의 수익을 얻는다.")
    @Test
    void 블랙잭이_아닌데_승리할_때의_수익_계산_테스트() {
        GameResult win = GameResult.WIN;
        BigDecimal profit = win.getProfitFrom(bettingAmount);

        BigDecimal expectedProfit = bettingAmount.getAmount().multiply(WIN_PROFIT_RATE);
        assertThat(profit).isEqualByComparingTo(expectedProfit);
    }

    @DisplayName("무승부라면 베팅 금액을 돌려 받는다")
    @Test
    void 무승부라면_베팅_금액을_돌려_받는다() {
        GameResult push = GameResult.PUSH;
        BigDecimal profit = push.getProfitFrom(bettingAmount);

        BigDecimal expectedProfit = bettingAmount.getAmount().multiply(PUSH_PROFIT_RATE);
        assertThat(profit).isEqualByComparingTo(expectedProfit);
    }

    @DisplayName("패배한다면 베팅 금액을 잃는다")
    @Test
    void 패배한다면_베팅_금액을_잃는다() {
        GameResult push = GameResult.LOSE;
        BigDecimal profit = push.getProfitFrom(bettingAmount);

        BigDecimal expectedProfit = bettingAmount.getAmount().multiply(LOSE_PROFIT_RATE);
        assertThat(profit).isEqualByComparingTo(expectedProfit);
    }
}
