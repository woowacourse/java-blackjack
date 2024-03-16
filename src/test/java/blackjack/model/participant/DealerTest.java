package blackjack.model.participant;

import static blackjack.model.deck.Score.FIVE;
import static blackjack.model.deck.Score.FOUR;
import static blackjack.model.deck.Score.SEVEN;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Shape.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import blackjack.model.deck.Deck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드의 총 점수가 16이하이면 카드를 추가로 받아야한다.")
    void shouldReceiveCardWhenTotalScoreIsUnder16() {
        Dealer dealer = Fixtures.createDealer();
        dealer.receiveInitialCards(List.of(new Card(CLOVER, FIVE), new Card(CLOVER, FOUR)));

        assertThat(dealer.shouldHit()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 총 점수가 16을 넘으면 카드를 추가로 받을 수 없다.")
    void couldNotReceiveCardWhenTotalScoreIsOver16() {
        Dealer dealer = Fixtures.createDealer();
        dealer.receiveInitialCards(List.of(new Card(CLOVER, SEVEN), new Card(CLOVER, TEN)));

        assertThat(dealer.shouldHit()).isFalse();
    }

    @Test
    @DisplayName("딜러는 2장의 카드를 받고 한 장의 카드만 공개한다.")
    void openFirstCard() {
        Dealer dealer = Fixtures.createDealer();
        dealer.receiveInitialCards(List.of(new Card(CLOVER, FIVE), new Card(CLOVER, FOUR)));

        assertThat(dealer.openFirstCard()).containsExactly(new Card(CLOVER, FIVE));
    }

    @Test
    @DisplayName("딜러는 초기 카드를 두 장씩 나눠준다.")
    void distributeTwoInitialCard() {
        Dealer dealer = Fixtures.createDealer();

        assertThat(dealer.distributeInitialCard()).hasSize(2);
    }

    static class Fixtures {
        static Dealer createDealer() {
            return new Dealer(new Deck());
        }
    }
}
