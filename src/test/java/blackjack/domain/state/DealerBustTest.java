package blackjack.domain.state;

import static blackjack.domain.CardFixture.SPADE_NINE;
import static blackjack.domain.CardFixture.SPADE_TEN;
import static blackjack.domain.CardFixture.SPADE_THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerBustTest {

    private State bust;

    @BeforeEach
    void setUp() {
        bust = Ready.dealToDealer(SPADE_THREE, SPADE_NINE)
                .draw(SPADE_TEN);
    }

    @Test
    @DisplayName("딜러가 bust면 DealerBust가 된다.")
    void dealer_bust() {
        assertThat(bust).isInstanceOf(DealerBust.class);
    }

    @Test
    @DisplayName("bust일 때 draw를 하면 에러가 발생한다.")
    void draw() {
        assertThatThrownBy(() -> bust.draw(SPADE_THREE))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("bust일 때 stay를 하면 에러가 발생한다.")
    void stay() {
        assertThatThrownBy(bust::stay)
                .isInstanceOf(IllegalStateException.class);
    }
}
