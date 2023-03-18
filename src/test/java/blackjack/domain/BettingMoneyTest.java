package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BettingMoneyTest {

    @Test
    @DisplayName("상금의 최솟값은 100이다.")
    void invalidByRangeTest1() {
        assertThatThrownBy(() -> new BettingMoney(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상금의 최소값은 100 최대 값은 1,000,000입니다.");
    }

    @Test
    @DisplayName("상금의 최댓값은 1,000,000이다.")
    void invalidByRangeTest2() {
        assertThatThrownBy(() -> new BettingMoney(1_000_001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상금의 최소값은 100 최대 값은 1,000,000입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {101, 102, 999_999})
    @DisplayName("상금은 10으로 나누어 떨어져야한다.")
    void intValidMultipleTest(int value) {
        assertThatThrownBy(() -> new BettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상금은 10배수로 나누어 떨어져야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 110, 120, 10_000, 1_000_000})
    @DisplayName("정상 상금 생성 테스트")
    void validTest(int value) {
        assertDoesNotThrow(() -> {
            new BettingMoney(value);
        });
    }
}
