package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -100})
    @DisplayName("베팅 금액이 0보다 작거나 같으면 예외를 던진다.")
    void throwException_whenBettingMoneyIsLessThan0(int wrongValue) {
        // expected
        assertThatThrownBy(() -> new BettingMoney(wrongValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 2074})
    @DisplayName("베팅 금액이 100 단위가 아니면 예외를 던진다.")
    void throwException_whenBettingMoneyIsNot100Multiple(int wrongUnit) {
        // expected
        assertThatThrownBy(() -> new BettingMoney(wrongUnit))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
