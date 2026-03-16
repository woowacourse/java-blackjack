package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {

    @Test
    void 베팅_금액을_생성한다() {
        BettingMoney money = new BettingMoney(1_000);
        assertThat(money.getAmount()).isEqualTo(1_000);
    }

    @Test
    void 양의_정수가_아니라면_예외가_발생한다() {
        assertThatThrownBy(() -> new BettingMoney(-1_000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("베팅 금액이 0이면 예외가 발생한다")
    @Test
    void 베팅_금액이_0이면_예외가_발생한다() {
        assertThatThrownBy(() -> new BettingMoney(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("같은 금액의 BettingMoney는 동등하다")
    @Test
    void 같은_금액의_BettingMoney는_동등하다() {
        BettingMoney money1 = new BettingMoney(1_000);
        BettingMoney money2 = new BettingMoney(1_000);
        assertThat(money1).isEqualTo(money2);
    }

    @DisplayName("다른 금액의 BettingMoney는 동등하지 않다")
    @Test
    void 다른_금액의_BettingMoney는_동등하지_않다() {
        BettingMoney money1 = new BettingMoney(1_000);
        BettingMoney money2 = new BettingMoney(2_000);
        assertThat(money1).isNotEqualTo(money2);
    }
}
