package domain.result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    Money money;

    @BeforeEach
    void setUp() {
        money = new Money(10000);
    }

    @DisplayName("승리시 1배 곱한다")
    @Test
    void applyWinProfitRate() {
        money.applyProfitRate(WinState.WIN);
        assertThat(money.getMoney()).isEqualTo(10000);
    }

    @DisplayName("패배시 -1배 곱한다")
    @Test
    void applyLoseProfitRate() {
        money.applyProfitRate(WinState.LOSE);
        assertThat(money.getMoney()).isEqualTo(-10000);
    }

    @DisplayName("블랙잭 승리시 1.5배 곱한다")
    @Test
    void applyBlackJackWinProfitRate() {
        money.applyProfitRate(WinState.BLACK_JACK);
        assertThat(money.getMoney()).isEqualTo(15000);
    }

    @DisplayName("무승부면 0배 곱한다")
    @Test
    void applyDrawProfitRate() {
        money.applyProfitRate(WinState.DRAW);
        assertThat(money.getMoney()).isEqualTo(0);
    }
}
