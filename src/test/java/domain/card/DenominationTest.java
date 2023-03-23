package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DenominationTest {
    @Test
    @DisplayName("블랙잭 게임 카드 숫자는 1~11의 값만 가진다.")
    void generateDenominationTest() {
        for (Denomination denomination : Denomination.values()) {
            Assertions.assertThat(denomination.getScore())
                    .isBetween(1, 11);
        }
    }
}
