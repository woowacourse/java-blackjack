package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.TestCardDeckCreator;
import blackjack.domain.card.TestHandCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("딜러 테스트")
class DealerTest {

    @DisplayName("딜러는 16점 이하이면 카드를 받을 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"6, 10", "5, 10", "4, 10"})
    void testCannotHit(int card1, int card2) {
        Dealer dealer = new Dealer(TestHandCreator.of(card1, card2));
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("딜러는 17점 이상이면 카드를 받을 수 없다")
    @ParameterizedTest
    @CsvSource(value = {"7, 10", "8, 10", "9, 10"})
    void testCanHit(int card1, int card2) {
        Dealer dealer = new Dealer(TestHandCreator.of(card1, card2));
        assertThat(dealer.canHit()).isFalse();
    }

    @DisplayName("히트 규칙을 기반으로 덱을 완성할 수 있다")
    @Test
    void testCompleteDealerHand() {
        Dealer dealer = new Dealer(TestHandCreator.of(4, 3));
        CardDeck deck = TestCardDeckCreator.createFrom(9, 10, 5, 4, 3);
        dealer.completeHand(deck);
        assertThat(dealer.calculateHandScore().getValue()).isEqualTo(19);
    }
}
