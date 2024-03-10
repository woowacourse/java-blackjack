package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Cards.Card;
import blackjack.domain.Cards.Hand;
import blackjack.domain.Cards.Rank;
import blackjack.domain.Cards.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    @DisplayName("카드의 합을 계산한다.")
    void deckCalculateTotalScore() {
        Hand hand = new Hand();
        hand.addCard(new Card(Shape.HEART, Rank.SEVEN));
        hand.addCard(new Card(Shape.HEART, Rank.JACK));

        int result = hand.calculateTotalScore();

        assertThat(result).isEqualTo(17);
    }

    @Test
    @DisplayName("카드가 없는 경우 예외가 발생한다.")
    void validateDeckTest() {
        Hand hand = new Hand();

        Assertions.assertThatThrownBy(hand::calculateTotalScore)
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("최적의 에이스 값, 11을 선택한다.")
    void calculateAceScoreWithSingleTest() {
        Hand hand = new Hand();
        hand.addCard(new Card(Shape.HEART, Rank.ACE));
        hand.addCard(new Card(Shape.HEART, Rank.JACK));

        int result = hand.calculateTotalScore();

        assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("최적의 에이스 값, 11과 1을 선택한다.")
    void calculateAceScoreWithDoubleTest() {
        Hand hand = new Hand();
        hand.addCard(new Card(Shape.HEART, Rank.ACE));
        hand.addCard(new Card(Shape.CLOVER, Rank.ACE));

        int result = hand.calculateTotalScore();

        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("최적의 에이스 값, 11, 1, 1을 선택한다.")
    void calculateAceScoreWithTripleTest() {
        Hand hand = new Hand();
        hand.addCard(new Card(Shape.HEART, Rank.ACE));
        hand.addCard(new Card(Shape.CLOVER, Rank.ACE));
        hand.addCard(new Card(Shape.DIAMOND, Rank.ACE));

        int result = hand.calculateTotalScore();

        assertThat(result).isEqualTo(13);
    }

    @Test
    @DisplayName("최적의 에이스 값, 1과 1을 선택한다.")
    void calculateAceScoreWithDiverseTest() {
        Hand hand = new Hand();
        hand.addCard(new Card(Shape.HEART, Rank.JACK));
        hand.addCard(new Card(Shape.CLOVER, Rank.NINE));
        hand.addCard(new Card(Shape.DIAMOND, Rank.ACE));
        hand.addCard(new Card(Shape.DIAMOND, Rank.ACE));

        int result = hand.calculateTotalScore();

        assertThat(result).isEqualTo(21);
    }
}
