package blackjack.domain.state;

import static blackjack.domain.CardFixture.SPADE_ACE;
import static blackjack.domain.CardFixture.SPADE_NINE;
import static blackjack.domain.CardFixture.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadyTest {

    @Test
    @DisplayName("ready에서 카드 두 장을 deal하고 카드 합이 21이 아니면 hit가 된다.")
    void ready_hit() {
        State state = Ready.dealToParticipant(SPADE_ACE, SPADE_NINE);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("ready에서 카드 두 장을 deal하고 카드 합이 21이면 blackjack이 된다.")
    void ready_blackjack() {
        State state = Ready.dealToParticipant(SPADE_ACE, SPADE_TEN);

        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("카드 두 장을 deal하면 두장의 카드를 가지고 있다.")
    void ready_get_cards() {
        State state = Ready.dealToParticipant(SPADE_ACE, SPADE_NINE);

        List<Card> actual = state.getCards().get();
        assertThat(actual).hasSize(2);
        assertThat(actual.get(0)).isEqualTo(SPADE_ACE);
        assertThat(actual.get(1)).isEqualTo(SPADE_NINE);
    }

    @Test
    @DisplayName("dealer의 상태에 대한 equals, hashCode, toString 테스트")
    void eq_hc_ts_dealer() {
        State o1 = Ready.dealToDealer(SPADE_ACE, SPADE_NINE);
        State o2 = Ready.dealToDealer(SPADE_ACE, SPADE_NINE);
        Object o = new Object();

        assertThat(o1.equals(o2)).isTrue();
        assertThat(o1.equals(o1)).isTrue();
        assertThat(o1.equals(o)).isFalse();
        assertThat(o1.hashCode() == o2.hashCode()).isTrue();
        assertThat(o1.toString()).isEqualTo(o2.toString());
    }

    @Test
    @DisplayName("participant의 상태에 대한 equals, hashCode, toString 테스트")
    void eq_hc_ts_participant() {
        State o1 = Ready.dealToParticipant(SPADE_ACE, SPADE_NINE);
        State o2 = Ready.dealToParticipant(SPADE_ACE, SPADE_NINE);
        Object o = new Object();

        assertThat(o1.equals(o2)).isTrue();
        assertThat(o1.equals(o1)).isTrue();
        assertThat(o1.equals(o)).isFalse();
        assertThat(o1.hashCode() == o2.hashCode()).isTrue();
        assertThat(o1.toString()).isEqualTo(o2.toString());
    }
}
