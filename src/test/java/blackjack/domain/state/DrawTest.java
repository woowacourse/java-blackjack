package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DrawTest {

    @Test
    @DisplayName("족보를 비교하여 같은 점수일 경우 무승부 상태가 된다.")
    void draw() {
        State state1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.NINE, Suit.CLUBS))
                .stand();
        State state2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.NINE, Suit.SPADES))
                .stand();
        assertThat(state1.judge(state2)).isInstanceOf(Draw.class);
    }

    @Test
    @DisplayName("족보를 비교하여 둘 다 블랙잭일 경우 무승부 상태가 된다.")
    void blackjackDraw() {
        State state1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.ACE, Suit.CLUBS));
        State state2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.ACE, Suit.SPADES));
        assertThat(state1.judge(state2)).isInstanceOf(Draw.class);
    }

    @Test
    @DisplayName("무승부시 수익 배율이 0이 되는지 확인한다.")
    void winRate() {
        State stand1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.NINE, Suit.CLUBS))
                .stand();
        State stand2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.NINE, Suit.SPADES))
                .stand();
        State draw = stand1.judge(stand2);
        assertThat(draw.prizeRate()).isEqualTo(0);
    }
}