package blackjack.model.participant;

import static blackjack.model.deck.Score.*;
import static blackjack.model.deck.Shape.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.model.deck.Card;
import blackjack.model.participant.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
    @Test
    @DisplayName("전달된 카드 리스트에 카드가 2개 미만인 경우 예외를 던진다.")
    void createCardsLowerSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Hand(List.of(new Card(CLOVER, ACE))))
                .withMessage("카드는 두 장 이상이어야 합니다.");
    }

    @Test
    @DisplayName("카드를 추가할 수 있다.")
    void addCard() {
        Hand hand = new Hand(List.of(new Card(CLOVER, FIVE), new Card(CLOVER, FOUR)));
        Hand addedCard = hand.addCard(new Card(CLOVER, ACE));
        assertThat(addedCard.getCards()).isEqualTo(List.of(new Card(CLOVER, FIVE), new Card(CLOVER, FOUR), new Card(CLOVER, ACE)));
    }

    @Test
    @DisplayName("제일 첫 번째 카드 하나를 꺼낸다.")
    void getFirstCard() {
        Hand hand = new Hand(List.of(new Card(CLOVER, FIVE), new Card(DIA, ACE)));
        assertThat(hand.getFirstCard()).isEqualTo(new Card(CLOVER, FIVE));
    }

    @Test
    @DisplayName("카드의 합을 계산할 수 있다.")
    void calculateScore() {
        Hand hand = new Hand(List.of(new Card(CLOVER, FIVE), new Card(CLOVER, FOUR)));
        assertThat(hand.calculateScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("Ace를 여러 개 가진 경우 ACE는 1점으로 계산한다.")
    void calculateScoreWithAces() {
        Hand hand = new Hand(List.of(new Card(SPADE, ACE), new Card(CLOVER, ACE)));
        assertThat(hand.calculateScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("Ace를 제외한 나머지 카드의 합계가 10을 초과하면 ACE는 1점으로 계산한다.")
    void calculateScoreWithAce() {
        Hand hand = new Hand(List.of(new Card(SPADE, ACE), new Card(CLOVER, NINE), new Card(CLOVER, TEN)));
        assertThat(hand.calculateScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("Ace를 제외한 나머지 카드의 합계가 10 이하인 경우 ACE는 11점으로 계산한다.")
    void calculateScoreWithAce2() {
        Hand hand = new Hand(List.of(new Card(SPADE, ACE), new Card(CLOVER, EIGHT), new Card(CLOVER, TWO)));
        assertThat(hand.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드들의 합이 임계 값을 넘으면 bust이다.")
    void isBust() {
        Hand hand = new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN), new Card(HEART, TEN)));
        assertThat(hand.isBust()).isTrue();
    }

    @Test
    @DisplayName("카드의 개수를 반환한다.")
    void countCardsSize() {
        Hand hand = new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN), new Card(HEART, TEN)));
        assertThat(hand.countSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("2장의 카드로 21인 경우 블랙잭이다.")
    void isBlackJack() {
        Hand hand = new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE)));
        assertThat(hand.isBlackJack()).isTrue();
    }
}
