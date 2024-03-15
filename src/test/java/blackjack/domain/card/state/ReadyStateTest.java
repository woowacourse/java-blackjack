package blackjack.domain.card.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyStateTest {
    @Test
    @DisplayName("블랙잭 게임을 준비하는 상태를 생성한다.")
    void create_ready_state_with_empty_cards() {
        final var state = new Ready();
        assertThat(state).isInstanceOf(Ready.class);
        assertThat(state.cards()
                        .toList()).isEmpty();
    }

    @Test
    @DisplayName("카드를 1장 드로우 하면 Ready 상태이다.")
    void one_draw_is_ready_state() {
        final var state = new Ready().draw(new Card(CardValue.ACE, CardSymbol.HEART));
        assertThat(state).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("카드 2장이면 Hit 상태가 된다.")
    void two_draw_is_hit_state() {
        final var state = new Ready().draw(new Card(CardValue.ACE, CardSymbol.HEART))
                                     .draw(new Card(CardValue.EIGHT, CardSymbol.DIAMOND));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드 2장의 합이 21이면 Blackjack 상태가 된다.")
    void if_sum_of_two_draw_21_is_blackjack_state() {
        final var state = new Ready().draw(new Card(CardValue.ACE, CardSymbol.HEART))
                                     .draw(new Card(CardValue.JACK, CardSymbol.DIAMOND));

        assertThat(state).isInstanceOf(Blackjack.class);
    }

}
