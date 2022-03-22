package blackjack_statepattern.state;

import static blackjack_statepattern.Fixture.SPADES_ACE;
import static blackjack_statepattern.Fixture.SPADES_JACK;
import static blackjack_statepattern.Fixture.SPADES_TEN;
import static blackjack_statepattern.Fixture.SPADES_TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack_statepattern.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitTest {
    @Test
    @DisplayName("히트 상태에서 드로우하고 21미만일 경우 드로우 가능")
    void hit_and_draw() {
        State state = new Hit(new Cards(SPADES_TWO, SPADES_JACK))
                .draw(SPADES_ACE);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("히트 상태에서 21을 초과하면 버스트")
    void hit_and_bust() {
        State state = new Hit(new Cards(SPADES_JACK, SPADES_TWO))
                .draw(SPADES_TEN);
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("히트 상태에서 stay를 하면 스테이 상태를 반환")
    void stay() {
        State state = new Hit(new Cards(SPADES_JACK, SPADES_TWO))
                .draw(SPADES_ACE)
                .stay();
        assertThat(state).isInstanceOf(Stay.class);
    }
}
