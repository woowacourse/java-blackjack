package blackjack.domain.card.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BustStateTest {
    @Test
    @DisplayName("카드의 합이 21을 초과하면 버스트 상태가 된다.")
    void create_bust_state_if_sum_of_cards_over_21() {
        final var state = new Ready().draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND))
                                     .draw(new Card(CardValue.FIVE, CardSymbol.DIAMOND))
                                     .draw(new Card(CardValue.JACK, CardSymbol.DIAMOND));

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("버스트 상태일 시 드로우 할 수 없다.")
    void can_not_draw() {
        final var state = new Ready().draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND))
                                     .draw(new Card(CardValue.FIVE, CardSymbol.DIAMOND))
                                     .draw(new Card(CardValue.JACK, CardSymbol.DIAMOND));

        final var card = new Card(CardValue.ACE, CardSymbol.HEART);

        Assertions.assertThatThrownBy(() -> state.draw(card))
                  .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("버스트 상태일 시 스탠드를 할 수 없다.")
    void can_not_change_stand_state() {
        final var state = new Ready().draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND))
                                     .draw(new Card(CardValue.FIVE, CardSymbol.DIAMOND))
                                     .draw(new Card(CardValue.JACK, CardSymbol.DIAMOND));

        Assertions.assertThatThrownBy(() -> state.stand())
                  .isInstanceOf(IllegalStateException.class);
    }
}
