package blackjack.domain.answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AnswerTest {

    @ParameterizedTest
    @DisplayName("예(y), 아니오(n)를 입력한 경우 정상적으로 나오는지 확인")
    @CsvSource(value = {
        "y,YES",
        "n,NO"
    })
    void answerTest(String value, String name) {
        Answer answer = Answer.of(value);

        assertThat(answer.name()).isEqualTo(name);
    }

    @Test
    @DisplayName("잘못된 값을 입력한 경우 예외 처리 확인")
    void answerErrorTest() {
        assertThatThrownBy(() -> Answer.of("d"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("예는 y, 아니오는 n을 입력해 주세요.");
    }
}
