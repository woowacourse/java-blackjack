package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackWinTest {
    @Test
    @DisplayName("게스트가 블랙잭일 때 상대가 블랙잭이 아니면 BlackjackWin 상태가 된다")
    void blackjackWin() {
        State state1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.ACE, Suit.CLUBS));
        State state2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.NINE, Suit.SPADES))
                .stand();
        assertThat(state1.judge(state2)).isInstanceOf(BlackjackWin.class);
    }

    @Test
    @DisplayName("블랙잭으로 이길 경우 수익 비율이 1.5배가 되는지 확인한다.")
    void blackjackWinRate() {
        State state1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.ACE, Suit.CLUBS));
        State state2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.NINE, Suit.SPADES))
                .stand();
        State blackjackWin =  state1.judge(state2);
        assertThat(blackjackWin.prizeRate()).isEqualTo(1.5);
    }
}