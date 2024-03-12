package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    @DisplayName("플레이어의 점수를 계산한다.")
    void calculateScoreTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.KING),
                new Card(Shape.HEART, Number.EIGHT)
        );
        Hand hand = new Hand(cards);
        // when, then
        assertThat(hand.getScore()).isEqualTo(new Score(18));
    }

    @Test
    @DisplayName("플레이어의 점수를 계산할 때, Ace의 점수를 유리한 방향(11)으로 결정한다.")
    void calculateAceAsElevenTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.TEN),
                new Card(Shape.CLOVER, Number.ACE)
        );
        Hand hand = new Hand(cards);
        // when, then
        assertThat(hand.getScore()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("플레이어의 점수를 계산할 때, Ace의 점수를 유리한 방향(1)으로 결정한다.")
    void calculateAceAsOneTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.TEN),
                new Card(Shape.CLOVER, Number.ACE),
                new Card(Shape.DIAMOND, Number.TEN)
        );
        Hand hand = new Hand(cards);
        // when, then
        assertThat(hand.getScore()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("블랙잭인 경우 올바르게 판단한다.")
    void isBlackJackTest() {
        // given
        List<Card> blackJackCards = List.of(
                new Card(Shape.SPADE, Number.ACE),
                new Card(Shape.HEART, Number.KING)
        );
        Hand blackJackHand = new Hand(blackJackCards);
        // when, then
        assertThat(blackJackHand.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("블랙잭이 아닌 경우를 올바르게 판단한다.")
    void nonBlackJackTest() {
        // given
        List<Card> nonBlackJackCards = List.of(
                new Card(Shape.SPADE, Number.ACE),
                new Card(Shape.HEART, Number.NINE),
                new Card(Shape.DIAMOND, Number.TWO)
        );
        Hand nonBlackJackHand = new Hand(nonBlackJackCards);
        // when, then
        assertThat(nonBlackJackHand.isBlackJack()).isFalse();
    }

    @Test
    @DisplayName("버스트인 경우 올바르게 판단한다.")
    void isBustTest() {
        // given
        List<Card> bustCards = List.of(
                new Card(Shape.SPADE, Number.KING),
                new Card(Shape.HEART, Number.KING),
                new Card(Shape.DIAMOND, Number.TWO)
        );
        Hand bustHand = new Hand(bustCards);
        // when, then
        assertThat(bustHand.isBusted()).isTrue();
    }

    @Test
    @DisplayName("버스트가 아닌 경우, 올바르게 판단한다.")
    void nonBustTest() {
        // given
        List<Card> nonBustCards = List.of(
                new Card(Shape.SPADE, Number.KING),
                new Card(Shape.HEART, Number.TEN)
        );
        // when, then
        Hand nonBustHand = new Hand(nonBustCards);
        assertThat(nonBustHand.isBusted()).isFalse();
    }

    @Test
    @DisplayName("손패의 점수를 올바르게 비교한다.")
    void scoreComparingTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.HEART, Number.TEN)
        );
        Hand hand = new Hand(cards);
        // when, then
        assertThat(hand.hasScoreGreaterThan(19)).isTrue();
        assertThat(hand.hasScoreGreaterThan(20)).isFalse();
    }
}
