package blackjack.domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    private static final Amount amount = new Amount(0);

    @Test
    void validateZero() {
        Assertions.assertThatThrownBy(() -> amount.updateAmount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1원 이상의 배팅금액을 입력해주세요.");
    }

    @Test
    void updateAmount() {
        Amount amount = new Amount(0);

        Assertions.assertThat(amount.updateAmount(10000)).isEqualTo(new Amount(10000));
    }
}
