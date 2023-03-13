package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusTest {

    @Test
    @DisplayName("of()는 두개의 인자를 받아 해당 이넘을 반환한다.")
    void blackjack() {
        // then
        Assertions.assertThat(Status.of(2,21)).isEqualTo(Status.BLACKJACK);
    }

    @Test
    @DisplayName("of()는 두개의 인자를 받아 해당 이넘을 반환한다.")
    void bust() {
        // then
        Assertions.assertThat(Status.of(2,22)).isEqualTo(Status.BUST);
    }

    @Test
    @DisplayName("of()는 두개의 인자를 받아 해당 이넘을 반환한다.")
    void none() {
        // then
        Assertions.assertThat(Status.of(2,20)).isEqualTo(Status.NONE);
    }
}
