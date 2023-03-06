package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardNumberTest {
    @DisplayName("블랙잭 게임 카드 숫자는 1~11의 값만 가진다.")
    @Test
    void generateNumberTest() {
        for (CardNumber cardNumber : CardNumber.values()) {
            Assertions.assertThat(cardNumber.getScore())
                    .isBetween(1, 11);
        }
    }
}
