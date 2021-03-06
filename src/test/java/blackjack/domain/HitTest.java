package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HitTest {
    @DisplayName("y 객체를 생성한다.")
    @Test
    void createHitY() {
        Hit hit = Hit.of("y");

        assertThat(hit).isEqualTo(Hit.YES);
    }

    @DisplayName("n 객체를 생성한다.")
    @Test
    void createHitN() {
        Hit hit = Hit.of("n");

        assertThat(hit).isEqualTo(Hit.NO);
    }

    @DisplayName("예외가 발생하는지 확인한다.")
    @Test
    void createHitException() {
        assertThatThrownBy(() -> {
            Hit.of("q");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("y가 입력되는 되는 경우를 확인한다.")
    @Test
    void isYes() {
        assertThat(Hit.isYes(Hit.of("y"))).isTrue();
    }
}
