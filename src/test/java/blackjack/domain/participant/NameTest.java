package blackjack.domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NameTest {

    @ParameterizedTest
    @DisplayName("이름이 공백인 경우 예외 처리 테스트")
    @ValueSource(strings = {"", "   ", "       "})
    void blankNameError(String input) {
        Assertions.assertThatThrownBy(() -> new Name(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름이 딜러인 경우 예외 처리 테스트")
    void dealerNameError() {
        Assertions.assertThatThrownBy(() -> new Name(Dealer.DEALER_NAME)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("유효성 검증 확인 - 성공")
    @ValueSource(strings = {"io", "jena", "pobi"})
    void blankNameSuccess(String input) {
        assertDoesNotThrow(() -> new Name(input));
    }
}
