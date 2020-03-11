package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class YesOrNoTest {

    @ParameterizedTest
    @DisplayName("생성 확인")
    @ValueSource(strings = {"y", "n", "Y", "N"})
    void create(String input) {
        assertThatCode(() -> YesOrNo.of(input))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("y, Y, n ,N이 아닌 경우 예외 처리")
    void createWithInvalidInput() {
        assertThatThrownBy(() -> YesOrNo.of("YES"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력은 y(Y), n(N)만 가능합니다.");
    }
}