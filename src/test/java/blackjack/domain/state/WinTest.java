package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinTest {

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