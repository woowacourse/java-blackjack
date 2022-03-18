package blackjack.domain.state;

import static blackjack.domain.CardFixture.SPADE_TWO;
import static blackjack.domain.CardFixture.SPADE_THREE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StayTest {

    private State stay;

    @BeforeEach
    void setUp() {
        stay = Ready.dealToParticipant(SPADE_TWO, SPADE_THREE)
                .stay();
    }

    @Test
    @DisplayName("stay일 때 draw를 하면 에러가 발생한다.")
    void draw() {
        assertThatThrownBy(() -> stay.draw(SPADE_THREE))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("stay일 때 stay를 하면 에러가 발생한다.")
    void stay() {
        assertThatThrownBy(stay::stay)
                .isInstanceOf(IllegalStateException.class);
    }
}
