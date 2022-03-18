package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StandTest {

    @Test
    @DisplayName("히트턴에서 스탠드하면 스탠드 상태가 된다.")
    void stand() {
        State state = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS));
        assertThat(state.stand()).isInstanceOf(Stand.class);
    }
}
