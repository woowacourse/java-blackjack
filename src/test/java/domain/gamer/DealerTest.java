package domain.gamer;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import domain.card.Card;
import domain.card.Hand;
import domain.card.cardinfo.CardNumber;
import domain.card.cardinfo.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("카드를 더 받을 수 있는지(Hit) 알려준다.")
    @Test
    void checkCanDealerHit() {
        Dealer dealer = new Dealer(new Hand());
        dealer.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        dealer.hit(new Card(CardNumber.THREE, CardShape.HEART));
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("패배(Bust)하지 않는 상황인지 알려준다.")
    @Test
    void checkUnderBustThreshold() {
        Dealer dealer = new Dealer(new Hand());
        dealer.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        dealer.hit(new Card(CardNumber.THREE, CardShape.HEART));
        assertThat(dealer.isNotBust()).isTrue();
    }

    @DisplayName("패배(Bust)한 상황인지 알려준다.")
    @Test
    void checkOverBustThreshold() {
        Dealer dealer = new Dealer(new Hand());
        dealer.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        dealer.hit(new Card(CardNumber.KING, CardShape.CLOVER));
        dealer.hit(new Card(CardNumber.KING, CardShape.SPADE));

        assertThat(dealer.isNotBust()).isFalse();
    }
}
