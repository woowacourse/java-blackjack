package blackjack.model.participant;

import static blackjack.model.fixture.HandFixture.OVER_16_UNDER_21_HAND;
import static blackjack.model.fixture.HandFixture.UNDER_16_HAND;
import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.FIVE;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Score.THREE;
import static blackjack.model.deck.Score.TWO;
import static blackjack.model.deck.Shape.CLOVER;
import static blackjack.model.deck.Shape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 딜러라는 이름을 가진다.")
    void getName() {
        Dealer dealer = new Dealer(UNDER_16_HAND.getHand());

        assertThat(dealer.getName()).isEqualTo(new Name("딜러"));
    }

    @Test
    @DisplayName("딜러는 카드를 받는다.")
    void hitDealer() {
        Dealer dealer = new Dealer(new Hand(List.of(Card.from(CLOVER, ACE), Card.from(CLOVER, THREE))));
        dealer.receiveCard(Card.from(HEART, TWO));

        assertThat(dealer.openCards())
                .isEqualTo(List.of(Card.from(CLOVER, ACE), Card.from(CLOVER, THREE), Card.from(HEART, TWO)));
    }

    @Test
    @DisplayName("딜러는 16이하이면 카드를 추가로 받을 수 있다.")
    void canReceive() {
        Dealer dealer = new Dealer(UNDER_16_HAND.getHand());
        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러는 16 초과면 카드를 추가로 받을 수 없다.")
    void canNotReceive() {
        Dealer dealer = new Dealer(OVER_16_UNDER_21_HAND.getHand());
        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("딜러는 2장의 카드를 받고 한 장의 카드만 공개한다.")
    void openCard() {
        Hand hand = new Hand(List.of(Card.from(CLOVER, TEN), Card.from(CLOVER, FIVE)));
        Dealer dealer = new Dealer(hand);
        assertThat(dealer.openCard()).containsExactly(Card.from(CLOVER, TEN));
    }
}
