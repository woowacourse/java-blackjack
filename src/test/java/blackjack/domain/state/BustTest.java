package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {
    @Test
    @DisplayName("버스트 상태에서는 상대의 패와 관계 없이 패배 상태가 된다.")
    void lose() {
        State bust1 = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .hit(new Card(Denomination.TWO, Suit.CLUBS));
        State bust2 = Ready.start(new Card(Denomination.TEN, Suit.CLUBS), new Card(Denomination.JACK, Suit.CLUBS))
                .hit(new Card(Denomination.TWO, Suit.CLUBS));

        State lose = bust1.judge(bust2);
        assertThat(lose).isInstanceOf(Lose.class);
    }
}