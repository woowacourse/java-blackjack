package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class YesOrNoTest {

    @ParameterizedTest(name = "입력값에 따라 Yes, No를 잘 반환하는지 테스트")
    @CsvSource(value = {"y,YES", "n,NO"})
    void find(String input, YesOrNo expected) {
        assertThat(YesOrNo.find(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력값이 잘못된 경우 예외 반환 테스트")
    void findInvalidInput() {
        assertThatThrownBy(() -> YesOrNo.find("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 또는 n을 입력해주세요.");
    }
}