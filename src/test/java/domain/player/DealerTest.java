package domain.player;

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
        dealer.hit(Card.valueOf(CardNumber.TEN, CardShape.HEART));
        dealer.hit(Card.valueOf(CardNumber.SIX, CardShape.HEART));
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("카드를 더 받을 수 없는지(Hit) 알려준다.")
    @Test
    void lowerThanCannotHitThresholdTest() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.valueOf(CardNumber.TEN, CardShape.HEART));
        dealer.hit(Card.valueOf(CardNumber.SEVEN, CardShape.HEART));

        assertThat(dealer.canHit()).isFalse();
    }
}
