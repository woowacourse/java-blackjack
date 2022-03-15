package blackJack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class YesOrNoTest {

    @Test
    @DisplayName("y가 입력될 때 YES를 반환하는지 테스트")
    void findYes() {
        assertThat(YesOrNo.find("y")).isEqualTo(YesOrNo.YES);
    }

    @Test
    @DisplayName("n이 입력될 때 NO를 반환하는지 테스트")
    void findNo() {
        assertThat(YesOrNo.find("n")).isEqualTo(YesOrNo.NO);
    }

    @ParameterizedTest(name = "y, n 외의 잘못된 값이 입력될 때 예외 테스트")
    @ValueSource(strings = {"Y", "N", "", " ", "yes"})
    void findExceptionByInvalidInput(String value) {
        assertThatThrownBy(() -> YesOrNo.find(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 또는 n을 입력해주세요.");
    }
}