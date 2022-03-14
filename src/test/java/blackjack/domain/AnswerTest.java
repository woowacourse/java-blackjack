package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.view.Answer;

class AnswerTest {

    @Test
    @DisplayName("입력 받은 값이 y, n이 아닐 시 에러를 발생시킨다.")
    void validate() {
        assertThatThrownBy(() -> Answer.from("Hi")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입려값이 y 또는 n이 아닙니다.");
    }

    @Test
    @DisplayName("Answer가 YES인지 NO인지 판단한다.")
    void isYes() {
        Answer answer = Answer.from("y");
        assertThat(answer.isYes()).isTrue();
    }
}