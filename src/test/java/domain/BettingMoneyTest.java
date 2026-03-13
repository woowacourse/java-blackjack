package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class BettingMoneyTest {

    @Test
    void 베팅_금액을_생성한다() {
        BettingMoney money = new BettingMoney(1000);
        assertThat(money.getAmount()).isEqualTo(1000);
    }

    @Test
    void 양의_정수가_아니라면_예외가_발생한다() {
        assertThatThrownBy(() -> new BettingMoney(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
