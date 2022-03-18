package blackjack.model.state;

import static blackjack.model.state.CardFixture.CLOVER_ACE;
import static blackjack.model.state.CardFixture.CLOVER_EIGHT;
import static blackjack.model.state.CardFixture.CLOVER_JACK;
import static blackjack.model.state.CardFixture.CLOVER_KING;
import static blackjack.model.state.CardFixture.CLOVER_TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {

    @DisplayName("카드를 한장 추가하고 카드 점수 합이 21 미만이면 Hit다.")
    @Test
    void hit() {
        State state = new Hit(new Cards(List.of(CLOVER_EIGHT, CLOVER_TWO))).add(CLOVER_JACK);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("카드를 한장 추가하고 카드 점수 합이 21 이면 Stay다.")
    @Test
    void stay() {
        State state = new Hit(new Cards(List.of(CLOVER_EIGHT, CLOVER_TWO))).add(CLOVER_ACE);

        assertThat(state).isInstanceOf(Stay.class);
    }

    @DisplayName("카드를 한장 추가하고 카드 점수 합이 21을 넘으면 Bust다.")
    @Test
    void bust() {
        State state = new Hit(new Cards(List.of(CLOVER_KING, CLOVER_TWO))).add(CLOVER_JACK);

        assertThat(state).isInstanceOf(Bust.class);
    }
}