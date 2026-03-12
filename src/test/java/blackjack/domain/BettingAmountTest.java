package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.deck.BettingAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingAmountTest {

    @Test
    @DisplayName("배팅 금액이 양수인지 검증")
    void validate_plus() {
        // given
        int input = -10000;

        // when & then
        assertThatThrownBy(() -> new BettingAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수");
    }
}
