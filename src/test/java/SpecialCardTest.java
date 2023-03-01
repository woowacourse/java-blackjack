import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialCardTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "M", "B", "A1"})
    @DisplayName("A, J, Q, K 이외의 값이 들어오면 실패한다.")
    void shouldFailDoesNotSpecial(String value) {
        Assertions.assertThat(SpecialCard.isSpecial(value)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "J", "Q", "K"})
    @DisplayName("A, J, Q, K가 들어오면 성공한다.")
    void shouldSuccessSpecial(String value) {
        Assertions.assertThat(SpecialCard.isSpecial(value)).isTrue();
    }
}
