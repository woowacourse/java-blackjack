package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.Card;
import blackjack.model.CardNumber;
import blackjack.model.CardShape;
import blackjack.model.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    @DisplayName("Ace를 포함하고 점수 총합이 11이하이면 총점에 추가점수를 더한다.")
    @Test
    void calculateContainsAceLessThan11() {
        List<Card> cards = List.of(
                new Card(CardNumber.ACE, CardShape.DIAMOND),
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.FIVE, CardShape.CLOVER)
        );
        Cards given = new Cards(cards);

        int score = given.calculateScore();

        assertThat(score).isEqualTo(18);
    }

    @DisplayName("점수 총합을 반환한다.")
    @Test
    void calculate() {
        List<Card> cards = List.of(
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.FIVE, CardShape.CLOVER),
                new Card(CardNumber.FIVE, CardShape.CLOVER)
        );
        Cards given = new Cards(cards);

        int score = given.calculateScore();

        assertThat(score).isEqualTo(12);
    }
}
