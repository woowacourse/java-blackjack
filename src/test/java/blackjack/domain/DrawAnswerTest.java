package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DrawAnswerTest {
    @DisplayName("정상 입력이 되는 경우를 확인한다.")
    @Test
    void findAnswer() {
        DrawAnswer drawAnswer = DrawAnswer.find("y");
        assertThat(drawAnswer).isEqualTo(DrawAnswer.YES);
    }

    @DisplayName("예외 입력이 되는 경우를 확인한다.")
    @Test
    void findAnswerException() {
        assertThatThrownBy(()->{
            DrawAnswer.find("q");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
