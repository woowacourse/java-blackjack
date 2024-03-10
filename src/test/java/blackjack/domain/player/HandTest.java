package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import fixture.CardFixture;
import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("참가자 소유 카드 테스트")
class HandTest {

    @DisplayName("초기 핸드의 사이즈는 2이다")
    @Test
    void testCreateInitialHandSize() {
        Hand hand = Hand.createHandFrom(CardDeck.createShuffledDeck());
        assertThat(hand.getCards().size()).isEqualTo(2);
    }

    @DisplayName("카드의 합을 계산할 수 있다.")
    @Test
    void testHandSummation() {
        Hand hand = HandFixture.of(2, 2, 2);
        assertThat(hand.calculateCardSummation()).isEqualTo(6);
    }

    @DisplayName("특정 카드를 핸드에 추가할 수 있다")
    @Test
    void testAppendCard() {
        Card card = CardFixture.from(2);
        Hand hand = HandFixture.of();
        hand.appendCard(card);
        assertThat(hand.getCards()).containsExactly(card);
    }

    @DisplayName("몇개의 카드를 더 뽑은 상태인지 확인할 수 있다")
    @Test
    void testCountPop() {
        CardDeck cardDeck = CardDeck.createShuffledDeck();
        Hand hand = Hand.createHandFrom(cardDeck);
        hand.appendCard(cardDeck.popCard());
        assertThat(hand.countPop()).isEqualTo(1);
    }

    @DisplayName("핸드에 에이스 카드가 몇개 있는지 확인할 수 있다")
    @Test
    void testCountAceInHand() {
        Hand hand = HandFixture.of(1, 1, 1, 3, 4, 5);
        assertThat(hand.countAce()).isEqualTo(3);
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드 없음")
    @Test
    void testCalculateScoreWithNoAce() {
        Hand hand = HandFixture.of(2, 3, 4, 5);
        assertThat(hand.calculateScore().getValue()).isEqualTo(14);
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드가 11로 이용됨")
    @Test
    void testCalculateScoreWithBigAce() {
        Hand hand = HandFixture.of(1, 10);
        assertThat(hand.calculateScore().getValue()).isEqualTo(21);
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드가 1로 이용됨")
    @Test
    void testCalculateScoreWithLowAce() {
        Hand hand = HandFixture.of(1, 10, 2);
        assertThat(hand.calculateScore().getValue()).isEqualTo(13);
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드 두개 이상")
    @Test
    void testCalculateScoreWithMultipleAce() {
        Hand hand = HandFixture.of(1, 1, 1);
        assertThat(hand.calculateScore().getValue()).isEqualTo(13);
    }
}
