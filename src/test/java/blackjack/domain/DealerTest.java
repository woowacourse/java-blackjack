package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DealerTest {

    @Test
    @DisplayName("카드를 받는다.")
    void test_receive_card() {
        Participant dealer = new Dealer("pobi", new ArrayList<>(), cards -> 0);
        Card card = new Card(CardType.DIAMOND, CardValue.TEN);
        dealer.receiveCard(card);
        assertThat(dealer.showCards().contains(card)).isTrue();
    }

    @Test
    @DisplayName("딜러는 한장의 카드만 보여준다.")
    void test_dealer_show_card() {
        //given
        Participant dealer = new Dealer("pobi", new ArrayList<>(), cards -> 0);
        dealer.receiveCard(new Card(CardType.DIAMOND, CardValue.TEN));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardValue.ACE));

        //when
        List<Card> cards = dealer.showCards();

        //then
        assertThat(cards).hasSize(1);
    }

    @Test
    @DisplayName("딜러가 카드를 한장을 더 뽑을 수 있는지 확인한다")
    void test_dealer_is_receive_card() {
        //given
        Participant dealer = new Dealer("pobi", new ArrayList<>(), cards -> 16);
        dealer.receiveCard(new Card(CardType.DIAMOND, CardValue.TEN));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardValue.ACE));
        //when
        assertThat(dealer.isReceiveCard()).isTrue();
        //then
    }
}
