package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CardsTest {

    @Test
    @DisplayName("갖고 있는 Cards 중 Ace가 없는 경우의 총점 계산")
    void calculateTotalScore() {
        // given
        Cards cards = new Cards(List.of(new Card(Number.K, Pattern.HEART), new Card(Number.FIVE, Pattern.DIAMOND)));

        // when
        int totalScore = cards.calculateTotalScore();

        // then
        assertThat(totalScore).isEqualTo(15);
    }

    @Test
    @DisplayName("갖고 있는 Cards 중 Ace가 있는 경우의 총점 계산")
    void calculateTotalScore_containsAce_11() {
        // given
        Cards cards = new Cards(List.of(
                new Card(Number.THREE, Pattern.HEART),
                new Card(Number.ACE, Pattern.DIAMOND),
                new Card(Number.FOUR, Pattern.CLUB))
        );

        // when
        int totalScore = cards.calculateTotalScore();

        // then
        assertThat(totalScore).isEqualTo(18);
    }

    @Test
    @DisplayName("갖고 있는 Cards 중 Ace가 있는 경우의 총점 계산")
    void calculateTotalScore_containsAce_1() {
        // given
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Pattern.HEART),
                new Card(Number.EIGHT, Pattern.DIAMOND),
                new Card(Number.SEVEN, Pattern.CLUB))
        );

        // when
        int totalScore = cards.calculateTotalScore();

        // then
        assertThat(totalScore).isEqualTo(16);
    }

    @Test
    @DisplayName("Ace를 포함한 두 장의 카드의 총점이 21인 경우")
    void calculateTotalScore_containsAce_blackjack() {
        // given
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Pattern.HEART),
                new Card(Number.K, Pattern.DIAMOND)
        ));

        // when
        int totalScore = cards.calculateTotalScore();

        // then
        assertThat(totalScore).isEqualTo(21);
     }
}
