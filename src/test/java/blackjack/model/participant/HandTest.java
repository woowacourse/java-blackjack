package blackjack.model.participant;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.FIVE;
import static blackjack.model.deck.Score.FOUR;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Score.THREE;
import static blackjack.model.deck.Shape.CLOVER;
import static blackjack.model.deck.Shape.DIA;
import static blackjack.model.deck.Shape.HEART;
import static blackjack.model.deck.Shape.SPADE;
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
                .isThrownBy(() -> new Hand(List.of(new Card(CLOVER, ACE))))
                .withMessage("카드는 두 장 이상이어야 합니다.");
    }

    @Test
    @DisplayName("카드를 추가할 수 있다.")
    void addCard() {
        Hand hand = new Hand(List.of(new Card(CLOVER, FIVE), new Card(CLOVER, FOUR)));
        hand.addCard(new Card(CLOVER, ACE));
        assertThat(hand.getCards()).isEqualTo(
                List.of(new Card(CLOVER, FIVE), new Card(CLOVER, FOUR), new Card(CLOVER, ACE)));
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
    @DisplayName("카드들의 합이 임계 값을 넘으면 bust이다.")
    void isBust() {
        Hand hand = new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN), new Card(HEART, TEN)));
        assertThat(hand.isBust()).isTrue();
    }

    @Test
    @DisplayName("2장의 카드로 21인 경우 블랙잭이다.")
    void isBlackJack() {
        Hand hand = new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE)));
        assertThat(hand.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("자신이 가진 카드의 개수가 다른 사람보다 큰지 확인한다.")
    void hasManySizeThanOtherCards() {
        Hand myHand = new Hand(List.of(new Card(DIA, TEN), new Card(DIA, ACE), new Card(DIA, THREE)));
        Hand otherHand = new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE)));

        assertThat(myHand.hasManyThan(otherHand)).isTrue();
    }
}
