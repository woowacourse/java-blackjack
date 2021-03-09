package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    @DisplayName("y 객체를 생성한다.")
    @Test
    void createDrawAnswerY() {
        Answer answer = Answer.of("y");
        assertThat(answer).isEqualTo(Answer.YES);
    }

    @DisplayName("n 객체를 생성한다.")
    @Test
    void createDrawAnswerN() {
        Answer answer = Answer.of("n");
        assertThat(answer).isEqualTo(Answer.NO);
    }

    @DisplayName("예외가 발생하는지 확인한다.")
    @Test
    void createDrawAnswerException() {
        assertThatThrownBy(() ->
                Answer.of("q"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("y가 입력되는 되는 경우를 확인한다.")
    @Test
    void isYes() {
        Answer yes = Answer.of("y");

        boolean isYes = yes.isYes();

        assertThat(isYes).isTrue();
    }
}
