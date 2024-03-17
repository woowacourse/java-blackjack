package blackjack.model.participant;

import static blackjack.model.deck.Score.*;
import static blackjack.model.deck.Shape.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.model.deck.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
    @Test
    @DisplayName("전달된 카드 리스트에 카드가 2개 미만인 경우 예외를 던진다.")
    void createCardsLowerSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Hand(List.of(Card.from(CLOVER, ACE))))
                .withMessage("카드는 두 장 이상이어야 합니다.");
    }

    @Test
    @DisplayName("카드를 추가할 수 있다.")
    void addCard() {
        Hand hand = new Hand(List.of(Card.from(CLOVER, FIVE), Card.from(CLOVER, FOUR)));
        Hand addedCard = hand.addCard(Card.from(CLOVER, ACE));
        assertThat(addedCard.getCards()).isEqualTo(List.of(Card.from(CLOVER, FIVE), Card.from(CLOVER, FOUR), Card.from(CLOVER, ACE)));
    }

    @Test
    @DisplayName("제일 첫 번째 카드 하나를 꺼낸다.")
    void getFirstCard() {
        Hand hand = new Hand(List.of(Card.from(CLOVER, FIVE), Card.from(DIA, ACE)));
        assertThat(hand.getFirstCard()).isEqualTo(Card.from(CLOVER, FIVE));
    }

    @Test
    @DisplayName("카드의 합을 계산할 수 있다.")
    void calculateScore() {
        Hand hand = new Hand(List.of(Card.from(CLOVER, FIVE), Card.from(CLOVER, FOUR)));
        assertThat(hand.calculateScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("Ace를 여러 개 가진 경우 ACE는 1점으로 계산한다.")
    void calculateScoreWithAces() {
        Hand hand = new Hand(List.of(Card.from(SPADE, ACE), Card.from(CLOVER, ACE)));
        assertThat(hand.calculateScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("Ace를 제외한 나머지 카드의 합계가 10을 초과하면 ACE는 1점으로 계산한다.")
    void calculateScoreWithAce() {
        Hand hand = new Hand(List.of(Card.from(SPADE, ACE), Card.from(CLOVER, NINE), Card.from(CLOVER, TEN)));
        assertThat(hand.calculateScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("Ace를 제외한 나머지 카드의 합계가 10 이하인 경우 ACE는 11점으로 계산한다.")
    void calculateScoreWithAce2() {
        Hand hand = new Hand(List.of(Card.from(SPADE, ACE), Card.from(CLOVER, EIGHT), Card.from(CLOVER, TWO)));
        assertThat(hand.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드의 개수를 반환한다.")
    void countCardsSize() {
        Hand hand = new Hand(List.of(Card.from(CLOVER, TEN), Card.from(SPADE, TEN), Card.from(HEART, TEN)));
        assertThat(hand.countSize()).isEqualTo(3);
    }
}
