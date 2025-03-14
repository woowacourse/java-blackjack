package participant;

import card.Card;
import card.CardNumber;
import card.CardType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드 패의 합이 16이 넘으면 카드를 받을 수 없다.")
    void test1() {
        // given
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardType.DIAMOND, CardNumber.TEN));
        dealer.hit(new Card(CardType.DIAMOND, CardNumber.SEVEN));

        // when
        boolean result = dealer.canReceiveCard();

        // then
        Assertions.assertThat(result)
                .isFalse();
    }

    @Test
    @DisplayName("딜러는 카드 패의 합이 16 넘지않으면 카드를 받을 수 있다.")
    void test2() {
        // given
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardType.DIAMOND, CardNumber.THREE));

        // when
        boolean result = dealer.canReceiveCard();

        // then
        Assertions.assertThat(result)
                .isTrue();
    }
}
