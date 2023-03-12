package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardNumberTest {
    @DisplayName("카드 숫자를 입력하면 해당하는 점수를 받을 수 있다.")
    @Test
    void Should_GetScore_When_InputNumber() {
        CardNumber ace = CardNumber.ACE;

        assertThat(ace.getScore()).isEqualTo(11);
    }
}
