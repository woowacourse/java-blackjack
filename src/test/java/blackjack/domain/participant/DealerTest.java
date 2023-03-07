package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DealerTest {

    @Test
    @DisplayName("카드 한장을 가져오는 테스트")
    void getOneCardTest() {
        Dealer dealer = new Dealer(new ArrayList<>());
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        dealer.draw(card1);
        dealer.draw(card2);

        List<String> expected = dealer.getOneCard();
        Assertions.assertThat(expected.size()).isEqualTo(1);
        Assertions.assertThat(expected).contains(card1.getCardName());
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16이하 인지 테스트")
    void canHitTest() {
        //given
        Dealer dealer = new Dealer(new ArrayList<>());
        Card card1 = new Card(Shape.CLOVER, Letter.SIX);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

        //when
        dealer.draw(card1);
        dealer.draw(card2);

        //then
        Assertions.assertThat(dealer.isHit()).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16초과 인지 테스트")
    void cantHitTest() {
        //given
        Dealer dealer = new Dealer(new ArrayList<>());
        Card card1 = new Card(Shape.CLOVER, Letter.SEVEN);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

        //when
        dealer.draw(card1);
        dealer.draw(card2);

        //then
        Assertions.assertThat(dealer.isHit()).isFalse();
    }
}
