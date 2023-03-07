package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CardsTest {

    @Test
    @DisplayName("갖고 있는 Cards 중 Ace가 없는 경우의 총점 계산")
    void calculateTotalScore() {
        // given
        Cards cards = new Cards(List.of(new Card(Number.K, Suit.HEART), new Card(Number.FIVE, Suit.DIAMOND)));

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
                new Card(Number.THREE, Suit.HEART),
                new Card(Number.ACE, Suit.DIAMOND),
                new Card(Number.FOUR, Suit.CLOVER))
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
                new Card(Number.ACE, Suit.HEART),
                new Card(Number.EIGHT, Suit.DIAMOND),
                new Card(Number.SEVEN, Suit.CLOVER))
        );

        // when
        int totalScore = cards.calculateTotalScore();

        // then
        assertThat(totalScore).isEqualTo(16);
    }

    @Test
    @DisplayName("Ace가 세 장 있는 경우 테스트")
    void calculateToTalScore_AceAceAce() {
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Suit.HEART),
                new Card(Number.ACE, Suit.SPADE),
                new Card(Number.ACE, Suit.CLOVER)
        ));

        int result = cards.calculateTotalScore();

        assertThat(result).isEqualTo(13);
    }

    @Test
    @DisplayName("Ace를 포함한 두 장의 카드의 총점이 21인 경우")
    void calculateTotalScore_containsAce_blackjack() {
        // given
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Suit.HEART),
                new Card(Number.K, Suit.DIAMOND)
        ));

        // when
        int totalScore = cards.calculateTotalScore();

        // then
        assertThat(totalScore).isEqualTo(21);
     }

    @Test
    @DisplayName("가지고 있는 카드가 블랙잭인지 확인")
    void isBlackjack() {
        // given
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Suit.HEART),
                new Card(Number.TEN, Suit.DIAMOND)
        ));

        // expect
        assertThat(cards.isBlackjack()).isTrue();
    }


    @Test
    @DisplayName("가지고 있는 카드가 버스트인지 확인")
    void isBust() {
        // given
        Cards cards = new Cards(List.of(
                new Card(Number.K, Suit.HEART),
                new Card(Number.TEN, Suit.DIAMOND),
                new Card(Number.J, Suit.CLOVER)
        ));

        // expect
        assertThat(cards.isBust()).isTrue();
    }
}
