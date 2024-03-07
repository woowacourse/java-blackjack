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
import model.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러의 카드 합계가 16점 이하면 true를 반환한다")
    @Test
    void testCanAddCard() {
        Cards cards = new Cards(List.of(new Card(ACE, HEART), new Card(JACK, HEART)));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isPossibleAddCard()).isTrue();
    }

    @DisplayName("딜러의 카드 합계가 17점 이상이면 false를 반환한다")
    @Test
    void testCanNotAddCard() {
        Cards cards = new Cards(List.of(new Card(SEVEN, HEART), new Card(JACK, HEART)));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isPossibleAddCard()).isFalse();
    }

    @DisplayName("카드 1장을 획득하면 카드가 1개 증가한 딜러 객체를 반환한다")
    @Test
    void shouldAddCardWhenAllowed() {
        Cards cards = new Cards(List.of(new Card(ACE, HEART), new Card(JACK, HEART)));
        Dealer dealer = new Dealer(cards);
        Card card = new Card(TWO, DIAMOND);
        Dealer updatedDealer = dealer.addCard(card);

        int expectedSize = cards.size() + 1;
        assertThat(updatedDealer.cardsSize()).isEqualTo(expectedSize);
    }
}
