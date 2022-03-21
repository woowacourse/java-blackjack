package blackjack.domain.state;


import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_JACK;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitTest {

    @Test
    @DisplayName("Hit 상태에서 한 장 추가로 받았을 때 Hit인 경우")
    void hit() {
        Cards cards = new Cards(List.of(SPADE_TWO, SPADE_JACK));

        State state = new Hit(cards);
        state = state.draw(Card.of(Denomination.ACE, Symbol.SPADE));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("Hit 상태에서 stay 시 Stay 정상 반환 테스트")
    void stay() {
        State state = new Hit(new Cards(List.of(SPADE_TWO, SPADE_JACK)))
            .draw(SPADE_ACE)
            .stay();

        assertThat(state).isInstanceOf(Stay.class);
    }

}
