package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TypeTest {
    @ParameterizedTest
    @DisplayName("타입 of")
    @ValueSource(strings = {"스페이드", "하트", "클로버", "다이아몬드"})
    void of(String type) {
        assertThat(Type.of(type)).isInstanceOf(Type.class);
    }

    @Test
    @DisplayName("존재하지 않는 타입")
    void of_NotExistType_ThrowException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Type.of("세모"));
    }

    @ParameterizedTest
    @DisplayName("심볼 이름 확인")
    @CsvSource(value = {"스페이드:스페이드", "하트:하트", "클로버:클로버", "다이아몬드:다이아몬드"}, delimiter = ':')
    void getName(String input, String expected) {
        assertThat(Type.of(input).getName()).isEqualTo(expected);
    }
}
