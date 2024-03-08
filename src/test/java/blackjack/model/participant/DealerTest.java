package blackjack.model.participant;

import static blackjack.model.deck.Score.FIVE;
import static blackjack.model.deck.Score.FOUR;
import static blackjack.model.deck.Shape.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    private Hand hand;
    private Dealer dealer;

    @BeforeEach
    void init() {
        hand = new Hand(List.of(new Card(CLOVER, FIVE), new Card(CLOVER, FOUR)));
        dealer = new Dealer(hand);
    }

    @Test
    @DisplayName("딜러는 16이하이면 카드를 추가로 받는다.")
    void canReceive() {
        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러는 2장의 카드를 받고 한 장의 카드만 공개한다.")
    void openCard() {
        assertThat(dealer.openCard()).containsExactly(new Card(CLOVER, FIVE));
    }
}
