package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DrawTest {

    @Test
    @DisplayName("족보를 비교하여 같은 레벨일 경우 무승부 상태가 된다.")
    void draw() {
        State state1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.NINE, Suit.CLUBS))
                .stand();
        State state2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.NINE, Suit.SPADES))
                .stand();
        assertThat(state1.judge(state2)).isInstanceOf(Draw.class);
    }
}