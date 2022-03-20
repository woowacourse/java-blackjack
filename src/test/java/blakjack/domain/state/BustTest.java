package blakjack.domain.state;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blakjack.domain.Fixture.CLUB_TEN;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BustTest {
    @Test
    @DisplayName("카드를 더 받으려고 하면 예외발생")
    void exception() {
        final PrivateArea privateArea = new PrivateArea("칙촉");
        final Chip chip = new Chip(1000);

        final Bust bust = new Bust(privateArea, chip);

        assertThatThrownBy(() -> bust.draw(CLUB_TEN))
                .isInstanceOf(IllegalStateException.class);
    }
}
