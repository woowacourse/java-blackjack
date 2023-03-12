package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BetAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {1_000, 50_000, 100_000})
    @DisplayName("조건에 맞는 베팅 금액 입력 시 베팅 금액 객체가 생성된다")
    void createSuccessTest(int amount) {
        BetAmount betAmount = new BetAmount();
        betAmount.initialize(amount);

        assertEquals(amount, betAmount.getValue());
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, 0, 999})
    @DisplayName("1,000원 미만의 금액 입력 시 예외를 반환한다")
    void exceptionUnderThousandTest(int amount) {
        BetAmount betAmount = new BetAmount();
        assertThatThrownBy(() -> betAmount.initialize(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1,000원 이상부터 가능합니다.");
    }

    @Test
    @DisplayName("100,000원 초과 금액 입력 시 예외를 반환한다")
    void exceptionOverHundredThousandTest() {
        BetAmount betAmount = new BetAmount();
        assertThatThrownBy(() -> betAmount.initialize(100_001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 10만원까지만 가능합니다.");
    }
}