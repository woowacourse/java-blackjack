package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DenominationTest {
    @ParameterizedTest
    @ValueSource(strings = {"B", "C", "?", "#", "", " "})
    @DisplayName("Denomination에 존재하는 name이외의 값이 들어오면 실패한다.")
    void shouldFailStrangeName(String name) {
        assertThatThrownBy(() -> Denomination.convertNumber(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 카드 형식이 아닙니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"A, 1", "2, 2", "3, 3", "4, 4", "5, 5", "6, 6", "7, 7", "8, 8", "9, 9", "J, 10", "Q, 10",
            "K, 10"})
    @DisplayName("Denomination에 존재하는 name의 값이 들어오면 해당하는 value값을 반환한다.")
    void shouldSuccessWhenCorrectName(String name, int value) {
        assertThat(Denomination.convertNumber(name)).isEqualTo(value);
    }
}
