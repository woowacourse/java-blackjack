package game;

import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardNumber;
import card.Pattern;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러는_게임_시작_시_두_장의_카드를_받는다() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.ACE),
                new Card(Pattern.DIAMOND, CardNumber.ACE)));

        assertThat(dealer.getHand().getCards()).hasSize(2);
    }

    @Test
    void 딜러는_게임_시작_시_첫_장을_공개한다() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.ACE),
                new Card(Pattern.DIAMOND, CardNumber.ACE)));

        Card singleCard = dealer.getSingleCard();

        assertThat(singleCard.equals(new Card(Pattern.CLOVER, CardNumber.ACE))).isTrue();
    }

    @Test
    void 딜러는_한_장의_카드를_받는다() {
        Dealer dealer = new Dealer();

        dealer.draw(List.of(new Card(Pattern.CLOVER, CardNumber.ACE)));

        assertThat(dealer.getHand().getCards()).hasSize(1);
    }

    @Test
    void 딜러가_보유한_카드의_합계를_구한다() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.SPADE, CardNumber.TEN)));

        int actual = dealer.calculateTotalPoints();

        assertThat(actual).isEqualTo(20);
    }

    @Test
    void 딜러가_버스트인지_확인한다_버스트() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.SPADE, CardNumber.TWO)
        ));

        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    void 딜러가_버스트인지_확인한다_버스트아님() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.SPADE, CardNumber.ACE)
        ));

        assertThat(dealer.isBust()).isFalse();
    }

    @Test
    void 딜러가_보유한_카드의_합계가_16을_넘었는지_판정한다_16초과() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.SPADE, CardNumber.SEVEN)
        ));

        assertThat(dealer.isOverDrawBound()).isTrue();
    }

    @Test
    void 딜러가_보유한_카드의_합계가_16을_넘었는지_판정한다_16이하() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.SPADE, CardNumber.SIX)
        ));

        assertThat(dealer.isOverDrawBound()).isFalse();
    }

}
