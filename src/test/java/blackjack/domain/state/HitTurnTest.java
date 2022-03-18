package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.state.HitTurn;
import blackjack.state.Ready;
import blackjack.state.State;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitTurnTest {

    @Test
    @DisplayName("처음 카드 두 장을 받고 블랙잭이 아니면 hit턴을 가진다.")
    void hitTurn() {
        State state = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS));
        assertThat(state).isInstanceOf(HitTurn.class);
    }
}
