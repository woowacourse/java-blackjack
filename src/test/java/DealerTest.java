import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class DealerTest {

    @DisplayName("카드를 한 장 받는다.")
    @Test
    void hitOneCard() {
        Dealer dealer = new Dealer();
        Card newCard = new Card(CardNumber.FIVE, CardShape.CLOVER);
        dealer.hit(newCard);

        assertThat(dealer.getCards().getCards()).contains(newCard);
    }

    @DisplayName("카드를 더 받을 수 있는지(Hit) 알려준다.")
    @Test
    void checkCanDealerHit() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        dealer.hit(new Card(CardNumber.THREE, CardShape.HEART));
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("패배(Bust)하지 않는 상황인지 알려준다.")
    @Test
    void checkUnderBustThreshold() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        dealer.hit(new Card(CardNumber.THREE, CardShape.HEART));
        assertThat(dealer.isNotBust()).isTrue();
    }

    @DisplayName("패배(Bust)한 상황인지 알려준다.")
    @Test
    void checkOverBustThreshold() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        dealer.hit(new Card(CardNumber.K, CardShape.CLOVER));
        dealer.hit(new Card(CardNumber.K, CardShape.SPADE));

        assertThat(dealer.isNotBust()).isFalse();
    }
}
