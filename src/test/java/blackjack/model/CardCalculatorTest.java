package blackjack.model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardCalculatorTest {

    @Test
    @DisplayName("카드의 총합이 10 이하일 경우 ace 카드의 점수는 11")
    void test_getTotalScore_when_ace_is_eleven() {
        List<Card> aceCards = List.of(new Card(Suit.SPADE, Rank.ACE));
        int totalScore = 10;
        CardCalculator cardCalculator = new CardCalculator();

        int result = cardCalculator.addAceScore(aceCards, totalScore);

        Assertions.assertThat(result).isEqualTo(totalScore + 11);
    }

    @Test
    @DisplayName("카드의 총합이 11 이상일 경우 ace 카드의 점수는 1")
    void test_getTotalScore_when_ace_is_one() {
        List<Card> aceCards = List.of(new Card(Suit.SPADE, Rank.ACE));
        int totalScore = 11;
        CardCalculator cardCalculator = new CardCalculator();

        int result = cardCalculator.addAceScore(aceCards, totalScore);

        Assertions.assertThat(result).isEqualTo(totalScore + 1);
    }
}
