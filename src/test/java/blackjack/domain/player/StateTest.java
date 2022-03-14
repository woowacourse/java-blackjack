package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StateTest {

    @Test
    @DisplayName("플레이어가 블랙잭일 경우 BLACKJACK 상태를 반환환다.")
    void isBlackjack() {
        Dealer dealer = new Dealer();
        dealer.takeCard(new Card(Denomination.ACE, Suit.HEART));
        dealer.takeCard(new Card(Denomination.JACK, Suit.SPADE));

        assertThat(State.from(dealer)).isEqualTo(State.BLACKJACK);
    }

    @Test
    @DisplayName("플레이어가 버스트일 경우 BUST 상태를 반환환다.")
    void isBust() {
        Dealer dealer = new Dealer();
        dealer.takeCard(new Card(Denomination.THREE, Suit.HEART));
        dealer.takeCard(new Card(Denomination.JACK, Suit.SPADE));
        dealer.takeCard(new Card(Denomination.QUEEN, Suit.CLOVER));

        assertThat(State.from(dealer)).isEqualTo(State.BUST);
    }

    @Test
    @DisplayName("플레이어가 블랙잭, 버스트가 아닐 경우 NONE 상태를 반환환다.")
    void isNone() {
        Dealer dealer = new Dealer();
        dealer.takeCard(new Card(Denomination.THREE, Suit.HEART));
        dealer.takeCard(new Card(Denomination.JACK, Suit.SPADE));

        assertThat(State.from(dealer)).isEqualTo(State.NONE);
    }
}
