package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DrawStatusTest {

    @Test
    @DisplayName("y 또는 n 으로 drawStatus 를 입력하면, drawStatus 는 정상 생성된다.")
    void createDrawStatus() {
        assertThatCode(() -> DrawStatus.from("y"))
                .doesNotThrowAnyException();

        assertThatCode(() -> DrawStatus.from("n"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("y 또는 n 가 아닌 다른 값을 입력하면, 예외를 발생한다.")
    void createDrawStatusException() {
        assertThatThrownBy(() -> DrawStatus.from("HIT"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y또는 n 응답으로 입력해주세요.");
    }
}
