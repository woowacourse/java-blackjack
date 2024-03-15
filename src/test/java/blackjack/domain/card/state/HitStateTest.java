package blackjack.domain.card.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HitStateTest {
    @Test
    @DisplayName("레디 상태에서 두장의 합이 21이 아닌상태에서만 Hit 상태가 된다.")
    void create_hit_state_with_two_cards_and_sum_of_cards_not_21() {
        final var state = new Ready().draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND))
                                     .draw(new Card(CardValue.FIVE, CardSymbol.DIAMOND));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("Hit 상태에서 드로우 해서 21이면 Stand 상태가 된다.")
    void if_sum_of_draw_21_is_stand_state() {
        final var sut = new Ready().draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND))
                                   .draw(new Card(CardValue.FIVE, CardSymbol.DIAMOND));

        final var state = sut.draw(new Card(CardValue.EIGHT, CardSymbol.HEART));

        assertThat(state).isInstanceOf(Stand.class);
    }

    @Test
    @DisplayName("Hit 상태에서 드로우 해서 21을 초과하면 Bust 상태가 된다.")
    void if_sum_of_draw_exceed_21_is_bust_state() {
        final var sut = new Ready().draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND))
                                   .draw(new Card(CardValue.FIVE, CardSymbol.DIAMOND));

        final var state = sut.draw(new Card(CardValue.JACK, CardSymbol.HEART));

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("Hit 상태에서 Stand 상태로 변환 가능하다.")
    void change_stand_state() {
        final var sut = new Ready().draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND))
                                   .draw(new Card(CardValue.FIVE, CardSymbol.DIAMOND));

        final var state = sut.stand();

        assertThat(state).isInstanceOf(Stand.class);
        assertThat(state.calculate()).isEqualTo(sut.calculate());
    }
}
