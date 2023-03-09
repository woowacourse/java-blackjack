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
        Assertions.assertThat(dealer.isNotBust()).isFalse();
    }
}
