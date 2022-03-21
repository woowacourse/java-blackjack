package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTurnTest {

    @Test
    @DisplayName("히트턴에서 스탠드하면 스탠드 상태가 된다.")
    void stand() {
        State state = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .stand();
        assertThat(state).isInstanceOf(Stand.class);
    }

    @Test
    @DisplayName("히트 턴에서 히트했는데 점수가 21이 넘으면 버스트 상태가 된다.")
    void bust() {
        State state = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .hit(new Card(Denomination.TWO, Suit.CLUBS));
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("히트 턴에서 히트했는데 점수가 21이 안넘으면 다시 히트턴 상태가 된다.")
    void hitTurn() {
        State state = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.FIVE, Suit.CLUBS))
                .hit(new Card(Denomination.TWO, Suit.CLUBS));
        assertThat(state).isInstanceOf(HitTurn.class);
    }
}
