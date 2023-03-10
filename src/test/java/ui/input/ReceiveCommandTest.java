package ui.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static ui.input.ReceiveCommand.HIT;
import static ui.input.ReceiveCommand.STAY;
import static ui.input.ReceiveCommand.isHit;
import static ui.input.ReceiveCommand.of;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReceiveCommandTest {

    @Test
    @DisplayName("ReceiveCommand가 만들어지는지 테스트한다.")
    void createReceiveCommandTest() {
        // given
        final ReceiveCommand hit = of("y");
        final ReceiveCommand stay = of("n");

        // when, then
        assertAll(
                () -> assertThat(hit).isEqualTo(HIT),
                () -> assertThat(stay).isEqualTo(STAY),
                () -> assertThatThrownBy(() -> of("something"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("y 또는 n을 입력해주세요.")
        );
    }

    @Test
    @DisplayName("isHit 메서드를 테스트한다.")
    void isHitTest() {
        // when, then
        assertAll(
                () -> assertThat(isHit(HIT)).isTrue(),
                () -> assertThat(isHit(STAY)).isFalse()
        );
    }
}
