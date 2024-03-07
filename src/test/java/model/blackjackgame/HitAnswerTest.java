package model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HitAnswerTest {

    @DisplayName("y 와 n 중 하나의 문자가 들어오면 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(strings = {"y", "n"})
    void testValidHitAnswer(String isHitAnswer) {
        assertThatCode(() -> HitAnswer.of(isHitAnswer)).doesNotThrowAnyException();
    }

    @DisplayName("y 와 n 이외의 문자가 들어오면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"예", "아니요", "Y", "N", "", "1", "2"})
    void testInvalidHitAnswer(String isHitAnswer) {
        assertThatThrownBy(() -> HitAnswer.of(isHitAnswer))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
