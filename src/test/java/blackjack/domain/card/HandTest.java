package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("참가자 소유 카드 테스트")
class HandTest {

    @DisplayName("초기 핸드의 사이즈는 2이다")
    @Test
    void createInitialHandSizeTest() {
        Hand hand = Hand.createHandFrom(CardDeck.createShuffledDeck());
        assertThat(hand.getCards().size()).isEqualTo(2);
    }

    @DisplayName("카드의 합을 계산할 수 있다.")
    @Test
    void handSummationTest() {
        Hand hand = TestHandCreator.of(2, 2, 2);
        assertThat(hand.calculateCardSummation()).isEqualTo(6);
    }

    @DisplayName("특정 카드를 핸드에 추가할 수 있다")
    @Test
    void appendCardTest() {
        Card card = TestCardCreator.from(2);
        Hand hand = TestHandCreator.of();
        hand.appendCard(card);
        assertThat(hand.getCards()).containsExactly(card);
    }

    @DisplayName("몇개의 카드를 더 뽑은 상태인지 확인할 수 있다")
    @Test
    void countPopTest() {
        CardDeck cardDeck = CardDeck.createShuffledDeck();
        Hand hand = Hand.createHandFrom(cardDeck);
        hand.appendCard(cardDeck.popCard());
        assertThat(hand.countDraw()).isEqualTo(1);
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드 없음")
    @Test
    void calculateScoreWithNoAceTest() {
        Hand hand = TestHandCreator.of(2, 3, 4, 5);
        assertThat(hand.calculateScore().getValue()).isEqualTo(14);
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드가 11로 이용됨")
    @Test
    void calculateScoreWithBigAceTest() {
        Hand hand = TestHandCreator.of(1, 10);
        assertThat(hand.calculateScore().getValue()).isEqualTo(21);
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드가 1로 이용됨")
    @Test
    void calculateScoreWithLowAceTest() {
        Hand hand = TestHandCreator.of(1, 10, 2);
        assertThat(hand.calculateScore().getValue()).isEqualTo(13);
    }

    @DisplayName("적절한 점수를 계산할 수 있다 - Ace 카드 두개 이상")
    @Test
    void calculateScoreWithMultipleAceTest() {
        Hand hand = TestHandCreator.of(1, 1, 1);
        assertThat(hand.calculateScore().getValue()).isEqualTo(13);
    }

    @DisplayName("손패가 블랙잭인지 알 수 있다")
    @Test
    void isBlackJackTest() {
        Hand hand = TestHandCreator.of(1, 10);
        assertThat(hand.isBlackJack()).isTrue();
    }

    @DisplayName("손패의 점수가 21이더라도 드로우 한 이력이 있으면 블랙잭이 아니다")
    @Test
    void isNotBlackJackTest() {
        Hand hand = TestHandCreator.of(2, 9, 10);
        assertThat(hand.isBlackJack()).isFalse();
    }
}
