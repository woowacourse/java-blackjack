package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DealerTest {
    @Test
    @DisplayName("딜러는 카드를 받으면 첫번째 카드를 공개할 수 있다.")
    void test3() {
        Dealer dealer = new Dealer();

        dealer.receiveCard(new Card(Denomination.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Denomination.TWO, Suit.SPADE));

        assertThat(dealer.openOneCard()).isEqualTo(new Card(Denomination.TEN, Suit.SPADE));
    }

    @Test
    @DisplayName("딜러는 카드를 추가로 받은 장 수를 공개할 수 있다. (시작할 때 2장을 받고, 게임 진행에서 추가로 받은 장 수)")
    void test4() {
        Dealer dealer = new Dealer();

        //기본 2장
        dealer.receiveCard(new Card(Denomination.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Denomination.JACK, Suit.SPADE));

        dealer.receiveCard(new Card(Denomination.ACE, Suit.CLUB));
        dealer.receiveCard(new Card(Denomination.TWO, Suit.CLUB));

        assertThat(dealer.getExtraHandSize()).isEqualTo(2);
    }
}
