package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

    @DisplayName("카드 합계를 구한다.")
    @Test
    public void calculateTotalCards() {
        Dealer dealer = new Dealer();
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        assertThat(dealer.calculateTotalValue()).isEqualTo(18);
    }

    @DisplayName("카드 합계가 16을 넘는지 확인한다. - 안 넘는 경우")
    @Test
    public void isUnderStandardTrue() {
        Dealer dealer = new Dealer();
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.TWO),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(dealer.isUnderStandard()).isTrue();
    }

    @DisplayName("카드 합계가 16을 넘는지 확인한다. - 넘는 경우")
    @Test
    public void isUnderStandardFalse() {
        Dealer dealer = new Dealer();
        dealer.distribute(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(dealer.isUnderStandard()).isFalse();
    }

//    @DisplayName("카드 합계가 16이하인 경우 카드를 한장 추가로 받는다.")
//    @Test
//    void draw() {
//        Dealer dealer = new Dealer();
//        dealer.distribute(new Cards(Arrays.asList(
//                new Card(Shape.SPACE, Value.EIGHT),
//                new Card(Shape.CLOVER, Value.KING)
//        )));
//        dealer.draw();
//        Cards cards = dealer.cards;
//        assertThat(cards.cards().size()).isEqualTo(3);
//    }
}
