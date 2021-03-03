package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("Dealer 객체를 생성한다.")
    @Test
    public void createDealer() {
        Dealer dealer = new Dealer();

        assertThat(dealer).isInstanceOf(Dealer.class);
    }

    @DisplayName("카드를 두장 분배받는다.")
    @Test
    public void distributeTwoCards() {
        Dealer dealer = new Dealer();
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        Cards cards = dealer.cards;
        assertThat(cards.cards().size()).isEqualTo(2);
    }

    @DisplayName("카드 합계가 16 이하인지 확인한다. - 카드를 더 받을 수 있다.")
    @Test
    public void isDrawableTrue() {
        Dealer dealer = new Dealer();
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.TWO),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(dealer.isDrawable()).isTrue();
    }

    @DisplayName("카드 합계가 17 이상인지 확인한다. - 카드를 더 받을 수 없다.")
    @Test
    public void isDrawableFalse() {
        Dealer dealer = new Dealer();
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.TWO),
                new Card(Shape.CLOVER, Value.KING),
                new Card(Shape.SPACE, Value.QUEEN)
        )));

        assertThat(dealer.isDrawable()).isFalse();
    }

    @DisplayName("카드 합계가 16이하인 경우 카드를 한장 추가로 받는다.")
    @Test
    void draw() {
        Dealer dealer = new Dealer();
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        dealer.draw();
        Cards cards = dealer.cards;
        assertThat(cards.cards().size()).isEqualTo(3);
    }

    @DisplayName("카드 한장은 공개하고 한장은 숨긴다.")
    @Test
    void show() {
        Dealer dealer = new Dealer();
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        List<Card> card = dealer.show();
        assertThat(card.size()).isEqualTo(1);
    }
}
