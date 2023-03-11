package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class HandTest {

    @Test
    @DisplayName("갖고 있는 Cards 중 Ace가 없는 경우의 총점 계산")
    void calculateTotalScore() {
        // given
        Hand hand = new Hand(List.of(new Card(Number.K, Pattern.HEART), new Card(Number.FIVE, Pattern.DIAMOND)));

        // when
        Score totalScore = hand.calculateScoreForBlackjack();

        // then
        assertThat(totalScore.getValue()).isEqualTo(15);
    }

    @Test
    @DisplayName("갖고 있는 Cards 중 Ace가 있는 경우의 총점 계산 - Ace의 점수가 11로 계산되는 경우")
    void calculateTotalScore_containsAce_11() {
        // given
        Hand hand = new Hand(List.of(
                new Card(Number.THREE, Pattern.HEART),
                new Card(Number.ACE, Pattern.DIAMOND),
                new Card(Number.FOUR, Pattern.CLUB))
        );

        // when
        Score totalScore = hand.calculateScoreForBlackjack();

        // then
        assertThat(totalScore.getValue()).isEqualTo(18);
    }

    @Test
    @DisplayName("갖고 있는 Cards 중 Ace가 있는 경우의 총점 계산 - Ace의 점수가 1로 계산되는 경우")
    void calculateTotalScore_containsAce_1() {
        // given
        Hand hand = new Hand(List.of(
                new Card(Number.ACE, Pattern.HEART),
                new Card(Number.EIGHT, Pattern.DIAMOND),
                new Card(Number.SEVEN, Pattern.CLUB))
        );

        // when
        Score totalScore = hand.calculateScoreForBlackjack();

        // then
        assertThat(totalScore.getValue()).isEqualTo(16);
    }

    @Test
    @DisplayName("Ace를 포함한 두 장의 카드의 총점이 21인 경우")
    void calculateTotalScore_containsAce_blackjack() {
        // given
        Hand hand = new Hand(List.of(
                new Card(Number.ACE, Pattern.HEART),
                new Card(Number.K, Pattern.DIAMOND)
        ));

        // when
        Score totalScore = hand.calculateScoreForBlackjack();

        // then
        assertThat(totalScore.getValue()).isEqualTo(21);
     }

    @Test
    @DisplayName("가지고 있는 카드가 버스트인지 확인")
    void isBust() {
        // given
        Hand hand = new Hand(List.of(
                new Card(Number.K, Pattern.HEART),
                new Card(Number.TEN, Pattern.DIAMOND),
                new Card(Number.J, Pattern.CLUB)
        ));

        // expect
        assertThat(hand.isBust()).isTrue();
    }

    @Test
    @DisplayName("가지고 있는 카드가 블랙잭인지 확인")
    void isBlackjack() {
        // given
        Hand hand = new Hand(List.of(
                new Card(Number.ACE, Pattern.HEART),
                new Card(Number.TEN, Pattern.DIAMOND)
        ));

        // expect
        assertThat(hand.isBlackjack()).isTrue();
    }
}
