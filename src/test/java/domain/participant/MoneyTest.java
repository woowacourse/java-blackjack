package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "", " ", "  "
    })
    void 입력이_공백이면_예외를_발생시킨다(String input) {
        // when & then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력이 공백이거나 null 입니다.");
    }

    @Test
    void 입력이_null이면_예외를_발생시킨다() {
        // when & then
        assertThatThrownBy(() -> new Money(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력이 공백이거나 null 입니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "만", "10000원"
    })
    void 입력이_정수_형식이_아니면_예외를_발생시킨다(String rawValue) {
        // when & then
        assertThatThrownBy(() -> new Money(rawValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력이 정수 형식이 아닙니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "0", "-1000"
    })
    void 입력이_0보다_작거나_같으면_예외를_발생시킨다(String rawValue) {
        // when & then
        assertThatThrownBy(() -> new Money(rawValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력은 0보다 커야 합니다.");
    }
}