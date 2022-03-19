package blackjack_statepattern;

import static blackjack_statepattern.Fixture.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BustTest {

    @Test
    @DisplayName("버스트인 상태에서 카드를 받지 못한다.")
    void bustDraw() {
        Bust bust = new Bust(new Cards(SPADES_JACK, SPADES_TWO, SPADES_TEN));
        assertThatThrownBy(
                () -> bust.draw(SPADES_ACE)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
