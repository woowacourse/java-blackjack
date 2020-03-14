package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TypeTest {
    @Test
    @DisplayName("타입 of")
    void of() {
        assertThat(Type.of("스페이드") == Type.SPADE).isTrue();
        assertThat(Type.of("하트") == Type.HEART).isTrue();
        assertThat(Type.of("클로버") == Type.CLOVER).isTrue();
        assertThat(Type.of("다이아몬드") == Type.DIAMOND).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 타입")
    void of_not_exist_type() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Type.of("세모"));
    }

    @ParameterizedTest
    @DisplayName("심볼 이름 확인")
    @CsvSource(value = {"스페이드:스페이드", "하트:하트", "클로버:클로버", "다이아몬드:다이아몬드"}, delimiter = ':')
    void getName(String input, String expected) {
        assertThat(Type.of(input).getName()).isEqualTo(expected);
    }
}
