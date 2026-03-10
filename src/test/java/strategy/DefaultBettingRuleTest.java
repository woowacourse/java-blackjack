package strategy;

import domain.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultBettingRuleTest {

    private DefaultBettingRule bettingRule;

    @BeforeEach
    void setUp() {
        bettingRule = new DefaultBettingRule();
    }

    @Test
    @DisplayName("블랙잭이면 베팅 금액의 1.5배를 받는다.")
    void 블랙잭_수익_계산() {
        assertThat(bettingRule.calculateBetAmount(1000, GameResult.WIN, true)).isEqualTo(1500);
    }

    @Test
    @DisplayName("블랙잭이 아니고 WIN이면 베팅 금액의 1배를 받는다.")
    void 일반_승리_수익_계산() {
        assertThat(bettingRule.calculateBetAmount(1000, GameResult.WIN, false)).isEqualTo(1000);
    }

    @Test
    @DisplayName("DRAW면 베팅 금액을 돌려받는다 (수익 0).")
    void 무승부_수익_계산() {
        assertThat(bettingRule.calculateBetAmount(1000, GameResult.DRAW, false)).isEqualTo(0);
    }

    @Test
    @DisplayName("LOSE면 베팅 금액을 잃는다.")
    void 패배_수익_계산() {
        assertThat(bettingRule.calculateBetAmount(1000, GameResult.LOSE, false)).isEqualTo(-1000);
    }
}