package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("딜러를 생성한다.")
    @Test
    void create() {
        Dealer dealer = new Dealer();
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @DisplayName("가진 카드 점수 총합이 16이하이면 true를 반환한다.(=카드를 더 뽑는다.)")
    @Test
    void canHit() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.SEVEN, Suit.SPADE));
        dealer.hit(new Card(Denomination.THREE, Suit.DIAMOND));
        assertThat(dealer.canHit()).isTrue();
    }

}
