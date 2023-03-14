package blackjack.domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BettingAmountTest {

    private static final BettingAmount BETTING_AMOUNT = new BettingAmount(0);

    @Test
    void validateZero() {
        Assertions.assertThatThrownBy(() -> BETTING_AMOUNT.updateAmount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1원 이상의 배팅금액을 입력해주세요.");
    }

    @Test
    void updateAmount() {
        BettingAmount bettingAmount = new BettingAmount(0);

        Assertions.assertThat(bettingAmount.updateAmount(10000)).isEqualTo(new BettingAmount(10000));
    }
}
