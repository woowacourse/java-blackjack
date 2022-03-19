package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinTest {
    @Test
    @DisplayName("블랙잭이 아니면서 상대보다 점수가 높으면 Win 상태가 되는지 확인한다.")
    void standWin() {
        State stand1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.TEN, Suit.CLUBS))
                .stand();
        State stand2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.NINE, Suit.SPADES))
                .stand();
        assertThat(stand1.judge(stand2)).isInstanceOf(Win.class);
    }

    @Test
    @DisplayName("블랙잭이 아니면서 상대가 버스트면 Win 상태가 되는지 확인한다.")
    void bustWin() {
        State stand1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.TEN, Suit.CLUBS))
                .stand();
        State stand2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.NINE, Suit.SPADES))
                .hit(new Card(Denomination.THREE, Suit.SPADES));
        assertThat(stand1.judge(stand2)).isInstanceOf(Win.class);
    }

    @Test
    @DisplayName("이기면 수익 배율이 1이 되는지 확인한다.")
    void winRate() {
        State stand1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.TEN, Suit.CLUBS))
                .stand();
        State stand2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.NINE, Suit.SPADES))
                .stand();
        State win = stand1.judge(stand2);
        assertThat(win.prizeRate()).isEqualTo(1);
    }
}