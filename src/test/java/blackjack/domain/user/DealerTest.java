package blackjack.domain.user;

import blackjack.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.state.CardFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DealerTest {
    public static final int GAME_OVER_SCORE = 21;

    @DisplayName("딜러는 첫 카드 2장을 받는다. 이때 첫 두장의 카드가 16초과이면 딜러의 턴은 끝난다. ")
    @Test
    void dealer_add_first_card() {
        //given
        State state = StateFactory.draw(SPADE_JACK, SPADE_TEN);

        //when
        Dealer dealer = new Dealer(state);

        //then
        assertThat(dealer.getState()).isInstanceOf(DealerTurnOver.class);
        assertThat(dealer.getState().isFinish()).isEqualTo(true);
    }

    @DisplayName("딜러의 첫 카드가 21점 초과하는 경우(A가 2장) 하나의 A를 1로 계산한다.")
    @Test
    void dealer_first_card_is_over() {
        //given
        State state = StateFactory.draw(SPADE_ACE, HEART_ACE);

        //when
        Dealer dealer = new Dealer(state);

        //then
        assertThat(dealer.getState().calculateScore()).isEqualTo(12);
        assertThat(dealer.getState()).isInstanceOf(Hit.class);
        assertThat(dealer.getState().isFinish()).isEqualTo(false);
    }

    @DisplayName("딜러의 첫 카드가 17점 이상인 경우 경우 딜러의 턴은 끝난다. ")
    @Test
    void dealer_is_game_over() {
        //given
        State state = StateFactory.draw(SPADE_JACK, HEART_TEN);

        //when
        Dealer dealer = new Dealer(state);

        //then
        assertThat(dealer.getState()).isInstanceOf(Finish.class);
        assertThatThrownBy(() -> dealer.getState().draw(SPADE_TWO))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
