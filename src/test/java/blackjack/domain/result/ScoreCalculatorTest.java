package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoreCalculatorTest {

    @Test
    @DisplayName("Ace가 포함되지 않은 카드의 합을 올바르게 계산한다.")
    void cardSumTestWithoutAce() {
        List<Card> cards = List.of(new Card(CardNumber.TWO, CardType.CLOVER),
                new Card(CardNumber.EIGHT, CardType.HEART),
                new Card(CardNumber.SIX, CardType.DIAMOND));

        assertThat(ScoreCalculator.cardSum(cards)).isEqualTo(16);
    }

    @Test
    @DisplayName("Ace가 포함된 카드의 합을 올바르게 계산한다.")
    void cardSumTestWithAcet() {
        List<Card> cards = List.of(new Card(CardNumber.ACE, CardType.CLOVER),
                new Card(CardNumber.TEN, CardType.HEART));

        assertThat(ScoreCalculator.cardSum(cards)).isEqualTo(21);
    }

    @Test
    @DisplayName("숫자의 합이 21을 넘는 경우 Ace는 1로 계산될 수 있다.")
    void cardSumTestWithoutAceBust() {
        List<Card> cards = List.of(new Card(CardNumber.ACE, CardType.CLOVER),
                new Card(CardNumber.ACE, CardType.HEART),
                new Card(CardNumber.ACE, CardType.DIAMOND));

        assertThat(ScoreCalculator.cardSum(cards)).isEqualTo(13);
    }
}
