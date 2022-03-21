package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {
    @Test
    @DisplayName("블랙잭 상태에서 상대가 블랙잭이 아니면 블랙잭 승리 상태가 된다.")
    void blackjackWin() {
        State blackjack = Ready.start(new Card(Denomination.ACE, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS));
        State stand = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .stand();
        State blackjackWin = blackjack.judge(stand);
        assertThat(blackjackWin).isInstanceOf(BlackjackWin.class);
    }

    @Test
    @DisplayName("블랙잭 상태에서 상대가 블랙잭이면 무승부 상태가 된다.")
    void blackjackDraw() {
        State blackjack1 = Ready.start(new Card(Denomination.ACE, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS));
        State blackjack2 = Ready.start(new Card(Denomination.ACE, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS));
        State draw = blackjack1.judge(blackjack2);
        assertThat(draw).isInstanceOf(Draw.class);
    }
}