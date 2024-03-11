package domain.gamer;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class DealerTest {

    @DisplayName("카드를 더 받을 수 있는지(Hit) 알려준다.")
    @Test
    void checkCanDealerHit() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        dealer.hit(new Card(CardNumber.THREE, CardShape.HEART));
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("카드를 더 받을 수 없는지(Hit) 알려준다.")
    @Test
    void lowerThanCannotHitThresholdTest() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.KING, CardShape.HEART));
        dealer.hit(new Card(CardNumber.SEVEN, CardShape.SPADE));

        assertThat(dealer.canHit()).isFalse();
    }
}
