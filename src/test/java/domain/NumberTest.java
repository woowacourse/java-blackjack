package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {
    @Test
    @DisplayName("블랙잭 게임 카드 숫자는 1~11의 값만 가진다.")
    void generateNumberTest() {
        for (Number number : Number.values()) {
            Assertions.assertThat(number.getNumber())
                    .isBetween(1, 11);
        }
    }
}
