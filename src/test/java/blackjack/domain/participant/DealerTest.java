package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DealerTest {

    @Test
    @DisplayName("딜러의 카드의 합이 16이하 인지 테스트")
    void canHitTest() {
        //given
        final Dealer dealer = new Dealer(new ArrayList<>());
        final Card card1 = new Card(Shape.CLOVER, Letter.SIX);
        final Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

        //when
        dealer.drawCard(card1);
        dealer.drawCard(card2);

        //then
        Assertions.assertThat(dealer.isHit()).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16초과 인지 테스트")
    void cantHitTest() {
        //given
        final Dealer dealer = new Dealer(new ArrayList<>());
        final Card card1 = new Card(Shape.CLOVER, Letter.SEVEN);
        final Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

        //when
        dealer.drawCard(card1);
        dealer.drawCard(card2);

        //then
        Assertions.assertThat(dealer.isHit()).isFalse();
    }
}
