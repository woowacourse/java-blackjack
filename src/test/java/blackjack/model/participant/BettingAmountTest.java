package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAmountTest {

    @Test
    @DisplayName("금액 입력 시 0 이하의 숫자인 경우")
    void createBettingAmountByNotPositiveNumber() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BettingAmount(-1))
                .withMessage("배팅 금액은 양수만 가능합니다.");
    }
}
