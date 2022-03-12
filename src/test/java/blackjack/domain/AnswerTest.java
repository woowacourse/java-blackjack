package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    @Test
    @DisplayName("입력값 y일 때 Yes를 반환한다.")
    void inputYAnswerYes() {
        assertThat(Answer.of("y")).isEqualTo(Answer.YES);
    }

    @Test
    @DisplayName("입력값 n일 때 No를 반환한다.")
    void inputNAnswerNo() {
        assertThat(Answer.of("n")).isEqualTo(Answer.NO);
    }

    @Test
    @DisplayName("입력값 y, n이 아닐 때 예외를 발생시킨다.")
    void inputAnswerException() {
        assertThatThrownBy(() -> Answer.of("g"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 응답입니다.");
    }

}