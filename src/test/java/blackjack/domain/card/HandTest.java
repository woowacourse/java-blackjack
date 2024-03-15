package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
    private static List<Card> createCards(List<Number> numbers) {
        return numbers.stream()
                .map(number -> new Card(number, Shape.CLOVER))
                .toList();
    }

    @DisplayName("블랙잭이 성립하는 패의 블랙잭 여부를 판단한다.")
    @Test
    void isHandBlackjack() {
        //given
        List<Card> cards = createCards(List.of(Number.ACE, Number.JACK));
        Hand hand = new Hand(cards);

        //when & then
        assertThat(hand.isBlackjack()).isEqualTo(true);
    }

    @DisplayName("블랙잭이 성립하지 않는 패의 블랙잭 여부를 판단한다.")
    @Test
    void isNotHandBlackjack() {
        //given
        List<Card> cards = createCards(List.of(Number.NINE, Number.FIVE, Number.KING));
        Hand hand = new Hand(cards);

        //when & then
        assertThat(hand.isBlackjack()).isEqualTo(false);
    }

    @DisplayName("블랙잭의 점수를 달성했지만 블랙잭이 아닌 패의 블랙잭 여부를 판단한다.")
    @Test
    void isBlackJackScoreButNot() {
        //given
        List<Card> cards = createCards(List.of(Number.ACE, Number.TWO, Number.EIGHT));
        Hand hand = new Hand(cards);

        //when & then
        assertThat(hand.isBlackjack()).isEqualTo(false);
    }

    @DisplayName("ACE를 포함한 패의 최적의 점수를 계산한다.")
    @Test
    void getOptimizedScoreWithAce() {
        //given
        List<Card> cards = createCards(List.of(Number.ACE, Number.JACK, Number.FIVE));
        Hand hand = new Hand(cards);
        int expectedScore = 16;

        //when
        int actualScore = hand.calculateOptimizedScore();

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @DisplayName("ACE를 포함하지 않은 패의 최적의 점수를 계산한다.")
    @Test
    void getOptimizedScoreWithoutAce() {
        //given
        List<Card> cards = createCards(List.of(Number.SEVEN, Number.JACK));
        Hand hand = new Hand(cards);
        int expectedScore = 17;

        //when
        int actualScore = hand.calculateOptimizedScore();

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @DisplayName("카드의 합이 주어진 값을 넘는다.")
    @Test
    void isTotalScoreGreaterThan() {
        //given
        List<Card> cards = createCards(List.of(Number.ACE, Number.JACK));
        Hand hand = new Hand(cards);
        int testScore = 20;

        //when & then
        assertThat(hand.isTotalScoreGreaterThan(testScore)).isEqualTo(true);
    }

    @DisplayName("카드의 합이 21이 넘는 경우 Bust인지 확인한다.")
    @Test
    void isBust() {
        //given
        List<Card> cards = createCards(List.of(Number.ACE, Number.FIVE, Number.NINE, Number.EIGHT));
        Hand hand = new Hand(cards);

        //when & then
        assertThat(hand.isBust()).isEqualTo(true);
    }
}
