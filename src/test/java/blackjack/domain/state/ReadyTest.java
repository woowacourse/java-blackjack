package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("처음 카드 두 장을 받고 블랙잭이 아니면 hit턴을 가지는지 확인한다.")
    void hitTurn() {
        State state = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS));
        assertThat(state).isInstanceOf(HitTurn.class);
    }

    @Test
    @DisplayName("처음 카드 두 장을 받고 블랙잭이면 블랙잭상태가 되는지 확인한다.")
    void blackjack() {
        State state = Ready.start(new Card(Denomination.ACE, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS));
        assertThat(state).isInstanceOf(Blackjack.class);
    }
}