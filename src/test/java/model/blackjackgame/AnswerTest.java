package model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerTest {

    @DisplayName("y 와 n 이외의 문자를 입력하면 예외 발생")
    @Test
    void testInValidateAnswer() {
        assertThatThrownBy(() -> new Answer("l")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("y 는 true, n 은 false 의 상태를 가진 객체 생성")
    @Test
    void testCollectObjectStatus() {
        Answer trueAnswer = new Answer("y");
        Answer falseAnswer = new Answer("n");
        assertAll(
                () -> assertTrue(trueAnswer.isHit()),
                () -> assertFalse(falseAnswer.isHit())
        );
    }
}
