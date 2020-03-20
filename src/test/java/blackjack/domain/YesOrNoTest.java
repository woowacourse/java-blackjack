package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class YesOrNoTest {

    @DisplayName("생성자 대소문자 치환 테스트")
    @ParameterizedTest
    @CsvSource(value = {"y, Y", "n, N"})
    void of_LowerAndUpperCase_Equals(String lower, String upper) {
        assertThat(YesOrNo.of(lower)).isEqualTo(YesOrNo.of(upper));
    }

    @DisplayName("생성자 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", "yes", "y "})
    @NullSource
    void of_NotYN_ExceptionThrown(String input) {
        assertThatThrownBy(() -> YesOrNo.of(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("유효");
    }

    @DisplayName("Yes는 isYes True, No는 isYes False 리턴")
    @Test
    void isYes_YesOrNo_YesTrueNoFalse() {
        assertThat(YesOrNo.of("y").isYes()).isTrue();
        assertThat(YesOrNo.of("n").isYes()).isFalse();
    }
}
