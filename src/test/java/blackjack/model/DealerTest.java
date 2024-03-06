package blackjack.model;

import static blackjack.model.Score.FIVE;
import static blackjack.model.Score.FOUR;
import static blackjack.model.Shape.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 16이하이면 카드를 추가로 받는다.")
    void canReceive() {
        Cards cards = new Cards(List.of(new Card(CLOVER, FIVE), new Card(CLOVER, FOUR)));
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러는 2장의 카드를 받고 한 장의 카드만 공개한다.")
    void openCard() {
        Cards cards = new Cards(List.of(new Card(CLOVER, FIVE), new Card(CLOVER, FOUR)));
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.openCard()).isEqualTo(new Card(CLOVER, FIVE));
    }
}
