package blackjack.domain.user;

import blackjack.domain.user.exception.HitOrStayException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HitOrStayTest {
    @Test
    void of() {
        assertThat(HitOrStay.of("y")).isEqualTo(HitOrStay.HIT);
        assertThat(HitOrStay.of("n")).isEqualTo(HitOrStay.STAY);
    }

    @Test
    void of_IllegalArgument_ShouldThrowException() {
        assertThatThrownBy(() -> {
            HitOrStay.of("k");
        }).isInstanceOf(HitOrStayException.class);
    }
}