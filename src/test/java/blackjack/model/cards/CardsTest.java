package blackjack.model.cards;

import static org.assertj.core.api.Assertions.assertThat;

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
        Cards given = new Cards();
        given.add(cards);

        int score = given.getScore();

        assertThat(score).isEqualTo(18);
    }

    @DisplayName("점수 총합을 반환한다.")
    @Test
    void getCardsScore() {
        List<Card> cards = List.of(
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.FIVE, CardShape.CLOVER),
                new Card(CardNumber.FIVE, CardShape.CLOVER)
        );
        Cards given = new Cards();
        given.add(cards);
        int score = given.getScore();

        assertThat(score).isEqualTo(12);
    }

    @DisplayName("점수 총합이 21점을 초과하는지 여부를 반환한다.")
    @Test
    void isGreaterThanWinningScore() {
        List<Card> cards = List.of(
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );

        Cards given = new Cards();
        given.add(cards);

        boolean result = given.isBust();

        assertThat(result).isTrue();
    }

    @DisplayName("카드를 지급받는다")
    @Test
    void addCard() {
        List<Card> cards = List.of(
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.FIVE, CardShape.CLOVER)
        );
        Cards given = new Cards();
        given.add(cards);
        Card card = new Card(CardNumber.ACE, CardShape.SPADE);

        given.add(card);

        assertThat(given.getCards()).hasSize(3);
    }
}
