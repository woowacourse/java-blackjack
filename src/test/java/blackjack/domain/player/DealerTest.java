package blackjack.domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드 점수 합이 16이하면 추가로 카드를 드로우 할 수 있다.")
    @Test
    void testCanDraw() {
        Dealer dealer = new Dealer("딜");
        dealer.draw(new Card(Type.CLUB, Denomination.TEN));
        dealer.draw(new Card(Type.CLUB, Denomination.THREE));

        assertThat(dealer.canDraw()).isEqualTo(true);
    }

    @DisplayName("딜러는 카드 점수 합이 16초과시 카드를 드로우 할 수 없다.")
    @Test
    void testCanNotDraw() {
        Dealer dealer = new Dealer("딜러");
        dealer.draw(new Card(Type.CLUB, Denomination.TEN));
        dealer.draw(new Card(Type.CLUB, Denomination.SEVEN));

        assertThat(dealer.canDraw()).isEqualTo(false);
    }

}
