package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void init() {
        dealer = new Dealer(new Hand(Collections.emptyList()));
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 17 미만이면 true 반환한다. (소프트 17 케이스는 제외)")
    void isCardValueUnder17() {
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.FIVE));
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.SIX));
        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 16 초과이면 false를 반환한다. (소프트 17 케이스는 제외)")
    void isCardValueOver16() {
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.TEN));
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.SEVEN));
        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 21 초과이면 false를 반환한다. (소프트 17 케이스는 제외)")
    void isCardValueOver21() {
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.TEN));
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.NINE));
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.THREE));
        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("소프트 17의 경우에는 false를 반환한다")
    void isFalseWhenSoft17() {
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.ACE));
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.SIX));
        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("가지고 있는 카드가 A 2장일 경우 true를 반환한다.")
    void isTrueWhenDoubleAce() {
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.ACE));
        dealer.takeCard(new Card(Suit.HEART, Denomination.ACE));
        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러는 Bust가 나지 않는 선에서 최선의 값을 선택한다.")
    void isDealerAlwaysChooseOptimalValue() {
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.ACE));
        dealer.takeCard(new Card(Suit.HEART, Denomination.ACE));
        dealer.takeCard(new Card(Suit.DIAMOND, Denomination.TEN));
        assertThat(dealer.canHit()).isTrue();
    }
}
