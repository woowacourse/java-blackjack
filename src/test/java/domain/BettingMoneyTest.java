package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @ParameterizedTest
    @DisplayName("유효하지 않은 배팅 금액이 들어오면 예외를 반환합니다.")
    @ValueSource(ints = {-1, 0, -10000})
    void validateBettingMoneyTest(int value) {
        Assertions.assertThatThrownBy(() -> new BettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 자연수여야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("배팅 금액이 만원단위가 아니면 예외를 반환합니다.")
    @ValueSource(ints = {1000, 10001, 20001, 25000})
    void validateBettingMoneyUnitTest(int value) {
        Assertions.assertThatThrownBy(() -> new BettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액의 단위는 10000원 입니다.");
    }
}