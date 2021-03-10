package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {

    @DisplayName("'y' 입력 시 YES를, 'n' 입력 시 NO 객체를 반환한다.")
    @Test
    void generate_test() {
        assertThat(Answer.YES.equals("y")).isTrue();
        assertThat(Answer.NO.equals("n")).isTrue();
    }
}