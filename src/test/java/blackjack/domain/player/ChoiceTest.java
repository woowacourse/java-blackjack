package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChoiceTest {

    @Test
    @DisplayName("y일 경우 HIT을 반환한다.")
    void hit() {
        assertThat(Choice.from("y")).isEqualTo(Choice.HIT);
    }

    @Test
    @DisplayName("n일 경우 STAY를 반환한다.")
    void stay() {
        assertThat(Choice.from("n")).isEqualTo(Choice.STAY);
    }

    @Test
    @DisplayName("y, n이 아닐 경우 예외를 발생시킨다.")
    void throwExceptionWhenNotYOrN() {
        assertThatThrownBy(() -> Choice.from("ellie"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
