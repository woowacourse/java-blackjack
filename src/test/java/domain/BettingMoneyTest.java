package domain;

import domain.participant.BettingMoney;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingMoneyTest {

    @Test
    void 베팅금액을_생성할_수_있다() {
        BettingMoney bettingMoney = BettingMoney.of(1000);
        assertThat(bettingMoney.getMoney()).isEqualTo(1000);
    }

    @Test
    void 베팅금액이_0이하면_예외가_발생한다() {
        assertThatThrownBy(() -> BettingMoney.of(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> BettingMoney.of(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
