package blackjack.domain.state;

import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.state.CardFactory.*;
import static org.assertj.core.api.Assertions.assertThat;

class HitTest {
    @DisplayName("Hit 상태에서 한번더 카드를 받을 때 점수가 21이하이면 hit을 반환한다. ")
    @Test
    void hitAdnHitTest() {
        Hit hit = new Hit(new Cards(SPADE_TEN, SPADE_TWO));
        State state = hit.draw(SPADE_ACE);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("Hit 상태에서 한번더 카드를 받을 때 점수가 21이 초과하면 Bust를 반환한다. ")
    @Test
    void hitAdnBustTest() {
        Hit hit = new Hit(new Cards(SPADE_TEN, SPADE_TWO));
        State state = hit.draw(SPADE_TEN);
        assertThat(state).isInstanceOf(Bust.class);
    }
}
