package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("덱의 합이 16 이하면 addable이다.")
    void isAddableTest() {
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(Shape.CLUB, Number.FIVE));
        dealer.addCard(new Card(Shape.DIAMOND, Number.FIVE));

        assertThat(dealer.isAddable()).isTrue();
    }

    @Test
    @DisplayName("덱의 합이 16 초과면 addable이지 않다.")
    void isNotAddableTest() {
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(Shape.CLUB, Number.FIVE));
        dealer.addCard(new Card(Shape.DIAMOND, Number.FIVE));
        dealer.addCard(new Card(Shape.CLUB, Number.TEN));

        assertThat(dealer.isAddable()).isFalse();
    }

    @Test
    @DisplayName("보이는 카드는 첫 카드이다.")
    void getVisibleCardTest() {
        Dealer dealer = new Dealer();
        Card card = new Card(Shape.CLUB, Number.FIVE);

        dealer.addCard(card);
        dealer.addCard(new Card(Shape.DIAMOND, Number.FIVE));

        assertThat(dealer.getVisibleCard()).containsExactly(card);
    }
}
