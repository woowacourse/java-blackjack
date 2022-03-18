package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {
    @Test
    @DisplayName("히트턴에서 히트한 후에 점수가 버스트이면 버스트 상태가 되는지 확인한다.")
    void bust() {
        State state = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .hit(new Card(Denomination.TWO, Suit.CLUBS));
        assertThat(state).isInstanceOf(Bust.class);
    }
}