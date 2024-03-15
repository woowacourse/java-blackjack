package model.participant;

import static model.card.CardNumber.JACK;
import static model.card.CardNumber.SEVEN;
import static model.card.CardNumber.SIX;
import static model.card.CardNumber.TWO;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러의 카드 합계가 16점 이하면 true를 반환한다")
    @Test
    void testCanHit() {
        List<Card> cards = List.of(new Card(SIX, HEART), new Card(JACK, HEART));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isPossibleHit()).isTrue();
    }

    @DisplayName("딜러의 카드 합계가 17점 이상이면 false를 반환한다")
    @Test
    void testCanNotHit() {
        List<Card> cards = List.of(new Card(SEVEN, HEART), new Card(JACK, HEART));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isPossibleHit()).isFalse();
    }

    @DisplayName("카드 1장을 획득하면 딜러의 카드가 1개가 증가한다")
    @Test
    void testHitCard() {
        Dealer dealer = new Dealer();
        Card card = new Card(TWO, DIAMOND);
        dealer.hitCard(card);
        assertThat(dealer.cardsSize()).isEqualTo(1);
    }

    @DisplayName("딜러가 처음 획득한 카드 1장을 반환한다")
    @Test
    void testGetFirstCard() {
        List<Card> cards = List.of(new Card(SIX, HEART), new Card(JACK, HEART));
        Dealer dealer = new Dealer(cards);
        Card firstCard = dealer.getFirstCard();
        assertThat(firstCard).hasToString("6하트");
    }
}
