package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DrawAnswerTest {
    @DisplayName("y 객체를 생성한다.")
    @Test
    void createDrawAnswerY() {
        DrawAnswer drawAnswer = DrawAnswer.of("y");

        assertThat(drawAnswer).isEqualTo(DrawAnswer.YES);
    }

    @DisplayName("n 객체를 생성한다.")
    @Test
    void createDrawAnswerN() {
        DrawAnswer drawAnswer = DrawAnswer.of("n");

        assertThat(drawAnswer).isEqualTo(DrawAnswer.NO);
    }

    @DisplayName("예외가 발생하는지 확인한다.")
    @Test
    void createDrawAnswerException() {
        assertThatThrownBy(() -> {
            DrawAnswer.of("q");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("y가 입력되는 되는 경우를 확인한다.")
    @Test
    void isYes() {
        assertThat(DrawAnswer.isYes(DrawAnswer.of("y"))).isTrue();
    }
}
