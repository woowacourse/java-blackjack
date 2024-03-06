package model;

import static model.CardNumber.JACK;
import static model.CardNumber.ONE;
import static model.CardNumber.SEVEN;
import static model.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러의 카드 합계가 16점 이하면 true를 반환한다")
    @Test
    void testCanAddCard() {
        Cards cards = new Cards(List.of(new Card(ONE, HEART), new Card(JACK, HEART)));
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
}
