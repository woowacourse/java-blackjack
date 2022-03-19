package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackWinTest {
    @Test
    @DisplayName("블랙잭으로 이길 경우 수익 비율이 1.5배가 되는지 확인한다.")
    void blackjackWinRate() {
        State state1 = Ready.start(new Card(Denomination.JACK, Suit.CLUBS), new Card(Denomination.ACE, Suit.CLUBS));
        State state2 = Ready.start(new Card(Denomination.JACK, Suit.SPADES), new Card(Denomination.NINE, Suit.SPADES))
                .stand();
        State blackjackWin = state1.judge(state2);
        assertThat(blackjackWin.prizeRate()).isEqualTo(1.5);
    }
}