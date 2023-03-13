package type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AnswerTest {

    @ParameterizedTest
    @CsvSource(value = {"y:true", "n:false"}, delimiter = ':')
    @DisplayName("입력값이 y이면 계속 진행, n이면 멈춤이다")
    void isMoreCardRequested(String answer, boolean isHit) {
        assertThat(Answer.isMoreCardRequested(answer)).isEqualTo(isHit);
    }

    @Test
    @DisplayName("입력값이 y, n 외의 값이 아니라면 에러가 발생한다.")
    void createWrongAnswer() {
        String answer = "a";

        assertThatThrownBy(() -> Answer.isMoreCardRequested(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("y 나 n 만을 입력해주세요.");
    }

}