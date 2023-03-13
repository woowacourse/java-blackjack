package blackjack.domain;

import blackjack.domain.participant.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AmountTest {

    @ParameterizedTest
    @ValueSource(strings = {"0","10000000","-1000"})
    @DisplayName("배팅금액이 1000~100000이 아닐 때 예외 처리")
    void checkAmountRange1(String amount) {
        assertThatThrownBy(() -> {
            new Amount(amount);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1000","10000","5000"})
    @DisplayName("배팅금액이 1000~100000일 때 테스트 통과")
    void checkAmountRange2(String amount) {
        assertDoesNotThrow(() -> {
            new Amount(amount);
        });
    }
}
