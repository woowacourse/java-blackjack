package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitStayTest {

    @DisplayName("y(Y) 또는 n(N)을 입력하면 해당하는 hit/stay 값을 반환한다.")
    @Test
    void hitOrStay() {
        assertThat(HitStay.of("Y")).isEqualTo(HitStay.HIT);
        assertThat(HitStay.of("N")).isEqualTo(HitStay.STAY);
    }

    @DisplayName("y(Y),n(N)외 값을 입력하면 예외를 던진다.")
    @Test
    void invalidValue() {
        assertThatThrownBy(() -> HitStay.of("oops"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Status가 Hit이면 true, 아니면 false를 반환한다.")
    @Test
    void isHit() {
        assertThat(HitStay.of("Y").isHit()).isTrue();
        assertThat(HitStay.of("N").isHit()).isFalse();
    }

}