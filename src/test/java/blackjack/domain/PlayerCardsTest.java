package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerCardsTest {
    @Test
    @DisplayName("한 장의 카드를 추가할 수 있다.")
    void addCardTest() {
        Card card = new Card(Shape.DIAMOND, Value.ACE);
        PlayerCards playerCards = new PlayerCards(new ArrayList<>());

        playerCards.append(card);
        List<Card> cards = playerCards.getCards();
        assertThat(cards).hasSize(1);
    }

    @Test
    @DisplayName("숫자 카드는 해당 숫자만큼의 점수로 계산된다.")
    void calculateScoreTest() {
        List<Card> cards = List.of(
                new Card(Shape.DIAMOND, Value.TWO),
                new Card(Shape.DIAMOND, Value.THREE),
                new Card(Shape.DIAMOND, Value.FOUR)
        );
        PlayerCards playerCards = new PlayerCards(cards);

        // TODO: score를 래핑해야 할 지도.
        int score = playerCards.calculateScore();

        assertThat(score).isEqualTo(9);
    }

    // TODO: ParameterizedTest 활용
    @Test
    @DisplayName("J, Q, K 카드는 모두 10으로 계산된다.")
    void calculateScoreWithAlphabetTest() {
        List<Card> cards = List.of(
                new Card(Shape.DIAMOND, Value.JACK),
                new Card(Shape.DIAMOND, Value.QUEEN),
                new Card(Shape.DIAMOND, Value.KING)
        );
        PlayerCards playerCards = new PlayerCards(cards);

        int score = playerCards.calculateScore();

        assertThat(score).isEqualTo(30);
    }

    @Test
    @DisplayName("ACE가 11점으로 계산되는 경우")
    void calculateScoreWithAceAsEleven() {
        List<Card> cards = List.of(
                new Card(Shape.DIAMOND, Value.JACK),
                new Card(Shape.DIAMOND, Value.ACE)
        );
        PlayerCards playerCards = new PlayerCards(cards);

        int score = playerCards.calculateScore();

        assertThat(score).isEqualTo(21);
    }

    @Test
    @DisplayName("ACE가 1점으로 계산되는 경우")
    void calculateScoreWithAceAsOne() {
        List<Card> cards = List.of(
                new Card(Shape.DIAMOND, Value.NINE),
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.SPADE, Value.ACE)
        );
        PlayerCards playerCards = new PlayerCards(cards);

        int score = playerCards.calculateScore();

        assertThat(score).isEqualTo(21);
    }

    @Test
    @DisplayName("ACE가 1점으로 계산되는 경우")
    void calculateScoreWithAceAsOne2() {
        List<Card> cards = List.of(
                new Card(Shape.DIAMOND, Value.KING),
                new Card(Shape.DIAMOND, Value.QUEEN),
                new Card(Shape.DIAMOND, Value.ACE)
        );
        PlayerCards playerCards = new PlayerCards(cards);

        int score = playerCards.calculateScore();

        assertThat(score).isEqualTo(21);
    }

    @Test
    @DisplayName("ACE가 1점으로 계산되는 경우")
    void calculateScoreWithAceAsOne3() {
        List<Card> cards = List.of(
                new Card(Shape.DIAMOND, Value.ACE),
                new Card(Shape.CLOVER, Value.ACE),
                new Card(Shape.SPADE, Value.ACE)
        );
        PlayerCards playerCards = new PlayerCards(cards);

        int score = playerCards.calculateScore();

        assertThat(score).isEqualTo(13);
    }
}
