package blackjack.domain.state;

import static blackjack.domain.CardFixture.SPADE_ACE;
import static blackjack.domain.CardFixture.SPADE_FOUR;
import static blackjack.domain.CardFixture.SPADE_NINE;
import static blackjack.domain.CardFixture.SPADE_TEN;
import static blackjack.domain.CardFixture.SPADE_THREE;
import static blackjack.domain.CardFixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerHitTest {

    @Test
    @DisplayName("카드 두 장을 받고 블랙잭이 아니고 점수가 16이하면 hit가 된다.")
    void hit() {
        State state = Ready.dealToDealer(SPADE_TEN, SPADE_TWO);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("hit일 때 draw를 하고 점수가 16이하면 아니면 hit가 된다.")
    void hit_draw_hit() {
        State state = Ready.dealToDealer(SPADE_TWO, SPADE_FOUR)
                .draw(SPADE_TEN);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("hit일 때 draw를 하고 점수가 16초과면면 stay가 된다.")
    void hit_draw_stay() {
        State state = Ready.dealToDealer(SPADE_THREE, SPADE_FOUR)
                .draw(SPADE_TEN);

        assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("hit일 때 draw를 하고 점수가 21이 초과하면 bust가 된다.")
    void hit_bust() {
        State state = Ready.dealToDealer(SPADE_THREE, SPADE_NINE)
                .draw(SPADE_TEN);

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("hit일 때 stay를 하면 stay가 된다.")
    void hit_stay() {
        State state = Ready.dealToDealer(SPADE_ACE, SPADE_TWO)
                .stay();

        assertThat(state).isInstanceOf(Stay.class);
    }
}
