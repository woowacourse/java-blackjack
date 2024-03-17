package blackjack.domain.card.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StandStateTest {

    @Test
    @DisplayName("Hit 상태에서 드로우 해서 21이면 Stand 상태가 된다.")
    void if_sum_of_draw_21_is_stand_state() {
        final var sut = new Ready().draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND))
                                   .draw(new Card(CardValue.FIVE, CardSymbol.DIAMOND));

        final var state = sut.draw(new Card(CardValue.EIGHT, CardSymbol.HEART));

        assertThat(state).isInstanceOf(Stand.class);
    }

    @Test
    @DisplayName("스탠드 상태일 시 드로우 할 수 없다.")
    void can_not_draw() {
        final var state = new Ready().draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND))
                                     .draw(new Card(CardValue.FIVE, CardSymbol.DIAMOND))
                                     .draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND));

        final var card = new Card(CardValue.ACE, CardSymbol.HEART);

        Assertions.assertThatThrownBy(() -> state.draw(card))
                  .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("스탠드 상태일 시 스탠드를 할 수 없다.")
    void can_not_change_stand_state() {
        final var state = new Ready().draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND))
                                     .draw(new Card(CardValue.FIVE, CardSymbol.DIAMOND));
        final var standState = state.stand();

        Assertions.assertThatThrownBy(() -> standState.stand())
                  .isInstanceOf(IllegalStateException.class);
    }
}
