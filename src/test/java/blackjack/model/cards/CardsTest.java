package blackjack.model.cards;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.vo.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    @DisplayName("Ace를 포함하고 점수 총합이 11이하이면 총점에 추가점수를 더한다")
    @Test
    void calculateContainsAceLessThan11() {
        List<Card> cards = List.of(
                new Card(CardNumber.ACE, CardShape.DIAMOND),
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.FIVE, CardShape.CLOVER)
        );
        Cards given = new Cards(cards);

        int score = given.getScoreValue();

        assertThat(score).isEqualTo(18);
    }

    @DisplayName("점수 총합을 반환한다")
    @Test
    void getCardsScore() {
        List<Card> cards = List.of(
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.FIVE, CardShape.CLOVER),
                new Card(CardNumber.FIVE, CardShape.CLOVER)
        );
        Cards given = new Cards(cards);

        int scoreValue = given.getScoreValue();

        assertThat(new Score(scoreValue)).isEqualTo(new Score(12));
    }

    @DisplayName("점수 총합이 21점을 초과하는지 여부를 반환한다")
    @Test
    void isGreaterThanWinningScore() {
        List<Card> cards = List.of(
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );

        Cards given = new Cards(cards);

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
        Cards given = new Cards(cards);
        Card card = new Card(CardNumber.ACE, CardShape.SPADE);

        given.add(card);

        assertThat(given.getCards()).hasSize(3);
    }

    @DisplayName("블랙잭인지 여부를 판별한다")
    @Test
    void isBlackJack() {
        List<Card> cards = List.of(
                new Card(CardNumber.ACE, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Cards given = new Cards(cards);

        assertThat(given.isBlackJack()).isTrue();
    }

    @DisplayName("블랙잭인지 여부를 판별한다")
    @Test
    void isNotBlackJack() {
        List<Card> cards = List.of(
                new Card(CardNumber.ACE, CardShape.HEART),
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.EIGHT, CardShape.CLOVER)
        );
        Cards given = new Cards(cards);

        assertThat(given.isBlackJack()).isFalse();
    }
}
