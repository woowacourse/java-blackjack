package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardNumberTest {
    @Test
    @DisplayName("카드 숫자에 맞는 점수를 반환한다.")
    void getScore() {
        Assertions.assertThat(CardNumber.TWO.getScore()).isEqualTo(2);
    }
}
