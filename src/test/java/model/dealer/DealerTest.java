package model.dealer;

import static model.card.CardNumber.JACK;
import static model.card.CardNumber.ACE;
import static model.card.CardNumber.SEVEN;
import static model.card.CardNumber.TWO;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러의 카드 합계가 16점 이하면 true를 반환한다")
    @Test
    void testCanAddCard() {
        Hand cards = new Hand(List.of(new Card(ACE, HEART), new Card(JACK, HEART)));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isPossibleHit()).isTrue();
    }

    @DisplayName("딜러의 카드 합계가 17점 이상이면 false를 반환한다")
    @Test
    void testCanNotAddCard() {
        Hand cards = new Hand(List.of(new Card(SEVEN, HEART), new Card(JACK, HEART)));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isPossibleHit()).isFalse();
    }

    @DisplayName("카드 1장을 획득하면 딜러의 카드가 1개가 증가한다")
    @Test
    void shouldAddCardWhenAllowed() {
        Hand cards = new Hand(List.of(new Card(ACE, HEART), new Card(JACK, HEART)));
        Dealer dealer = new Dealer(cards);
        Card card = new Card(TWO, DIAMOND);
        dealer.hitCard(card);
        assertThat(dealer.handSize()).isEqualTo(3);
    }
}
