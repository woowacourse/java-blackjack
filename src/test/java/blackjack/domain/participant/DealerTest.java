package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("카드 한장을 가져오는 테스트")
    void getOneCardTest() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        dealer.drawCard(card1);
        dealer.drawCard(card2);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16이하 인지 테스트")
    void canHitTest() {
        //given
        Dealer dealer = new Dealer();
        Card card1 = new Card(Shape.CLOVER, Letter.SIX);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

        //when
        dealer.drawCard(card1);
        dealer.drawCard(card2);

        //then
        Assertions.assertThat(dealer.canNotHit()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16초과 인지 테스트")
    void cantHitTest() {
        //given
        Dealer dealer = new Dealer();
        Card card1 = new Card(Shape.CLOVER, Letter.SEVEN);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

        //when
        dealer.drawCard(card1);
        dealer.drawCard(card2);

        //then
        Assertions.assertThat(dealer.canNotHit()).isTrue();
    }

    @Test
    @DisplayName("패를 한장 빼고 뒤집는 테스트")
    void reverseAllExceptionOneTest() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Shape.CLOVER, Letter.SEVEN);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        Card card3 = new Card(Shape.HEART, Letter.TWO);
        Card card4 = new Card(Shape.SPADE, Letter.TWO);
        dealer.drawCard(card1);
        dealer.drawCard(card2);
        dealer.drawCard(card3);
        dealer.drawCard(card4);

        dealer.reverseAllExceptOne();

        Assertions.assertThat(dealer.getCards().get(0).isOpen()).isTrue();
        for (int i = 1; i < dealer.getCards().size(); i++) {
            Assertions.assertThat(dealer.getCards().get(i).isOpen()).isFalse();
        }
    }

    @Test
    @DisplayName("카드 모두 여는 테스트")
    void openAllCardTest() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Shape.CLOVER, Letter.SEVEN);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        Card card3 = new Card(Shape.HEART, Letter.TWO);
        Card card4 = new Card(Shape.SPADE, Letter.TWO);
        dealer.drawCard(card1);
        dealer.drawCard(card2);
        dealer.drawCard(card3);
        dealer.drawCard(card4);

        dealer.reverseAllExceptOne();
        dealer.openAllCard();

        for (int i = 0; i < dealer.getCards().size(); i++) {
            Assertions.assertThat(dealer.getCards().get(i).isOpen()).isTrue();
        }
    }
}
