package ui.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ReceiveCommandTest {

    @Test
    @DisplayName("ReceiveCommand가 만들어지는지 테스트한다.")
    void createReceiveCommandTest() {
        // given
        final ReceiveCommand hit = ReceiveCommand.of("y");
        final ReceiveCommand stay = ReceiveCommand.of("n");

        // when, then
        assertAll(
                () -> assertThat(hit).isEqualTo(ReceiveCommand.HIT),
                () -> assertThat(stay).isEqualTo(ReceiveCommand.STAY),
                () -> assertThatThrownBy(() -> ReceiveCommand.of("something"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("y 또는 n을 입력해주세요.")
        );
    }
}
