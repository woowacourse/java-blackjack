package blackjack.domain.state;

import static blackjack.domain.CardFixture.SPADE_ACE;
import static blackjack.domain.CardFixture.SPADE_TWO;
import static blackjack.domain.CardFixture.SPADE_THREE;
import static blackjack.domain.CardFixture.SPADE_NINE;
import static blackjack.domain.CardFixture.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitTest {

    @Test
    @DisplayName("카드 두 장을 받고 블랙잭이 아니면 hit가 된다.")
    void hit() {
        State state = Ready.deal(SPADE_ACE, SPADE_TWO);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("hit일 때 draw를 하고 bust가 아니면 hit가 된다.")
    void hit_draw() {
        State state = Ready.deal(SPADE_ACE, SPADE_TWO)
                .draw(SPADE_TEN);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("hit일 때 draw를 하고 21이 초과하면 bust가 된다.")
    void hit_bust() {
        State state = Ready.deal(SPADE_THREE, SPADE_NINE)
                .draw(SPADE_TEN);

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("hit일 때 draw를 하고 21이 되어도 hit가 된다.")
    void hit_not_blackjack_21() {
        State state = Ready.deal(SPADE_NINE, SPADE_TWO)
                .draw(SPADE_TEN);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("hit일 때 stay를 하면 stay가 된다.")
    void hit_stay() {
        State state = Ready
                .deal(SPADE_ACE, SPADE_TWO)
                .stay();

        assertThat(state).isInstanceOf(Stay.class);
    }
}
