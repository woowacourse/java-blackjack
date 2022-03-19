package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoseTest {
    @Test
    @DisplayName("버스트 상태는 무조건 패배 결과가 된다.")
    void bustLose() {
        State bust1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.TEN, Suit.CLUBS))
                .hit(new Card(Denomination.TWO, Suit.CLUBS));
        State bust2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.TEN, Suit.SPADES))
                .hit(new Card(Denomination.TWO, Suit.SPADES));
        assertThat(bust1.judge(bust2)).isInstanceOf(Lose.class);
    }

    @Test
    @DisplayName("상대보다 족보가 낮으면 패배 결과가 된다.")
    void standLose() {
        State stand1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.NINE, Suit.CLUBS))
                .stand();
        State stand2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.TEN, Suit.SPADES))
                .stand();
        assertThat(stand1.judge(stand2)).isInstanceOf(Lose.class);
    }
    @Test
    @DisplayName("지면 수익 배율이 -1이 되는지 확인한다.")
    void loseRate() {
        State stand1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.NINE, Suit.CLUBS))
                .stand();
        State stand2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.TEN, Suit.SPADES))
                .stand();
        State win = stand1.judge(stand2);
        assertThat(win.prizeRate()).isEqualTo(-1);
    }
}