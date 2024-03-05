package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static blackjack.domain.card.Shape.*;
import static blackjack.domain.card.Value.*;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerCardsTest {
    @Test
    @DisplayName("한 장의 카드를 추가할 수 있다.")
    void addCardTest() {
        Card card = new Card(DIAMOND, ACE);
        PlayerCards playerCards = PlayerCards.createEmptyCards();

        playerCards.append(card);
        List<Card> cards = playerCards.getCards();
        assertThat(cards).hasSize(1);
    }

    @Test
    @DisplayName("숫자 카드는 해당 숫자만큼의 점수로 계산된다.")
    void calculateScoreTest() {
        List<Card> cards = List.of(
                new Card(DIAMOND, TWO),
                new Card(DIAMOND, THREE),
                new Card(DIAMOND, FOUR)
        );
        PlayerCards playerCards = new PlayerCards(cards);

        // TODO: score를 래핑해야 할 지도.
        int score = playerCards.calculateScore();

        assertThat(score).isEqualTo(9);
    }

    @Test
    @DisplayName("J, Q, K 카드는 모두 10으로 계산된다.")
    void calculateScoreWithAlphabetTest() {
        List<Card> cards = List.of(
                new Card(DIAMOND, JACK),
                new Card(DIAMOND, QUEEN),
                new Card(DIAMOND, KING)
        );
        PlayerCards playerCards = new PlayerCards(cards);

        int score = playerCards.calculateScore();

        assertThat(score).isEqualTo(30);
    }

    @ParameterizedTest
    @MethodSource("cardsAndScore")
    @DisplayName("ACE 는 1점 혹은 11점으로 계산된다.")
    void calculateScoreWithAce(List<Card> cards, int expected) {
        PlayerCards playerCards = new PlayerCards(cards);
        int score = playerCards.calculateScore();

        assertThat(score).isEqualTo(expected);
    }

    static Stream<Arguments> cardsAndScore() {
        return Stream.of(
                Arguments.arguments(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, ACE)), 21),
                Arguments.arguments(List.of(new Card(DIAMOND, NINE), new Card(DIAMOND, ACE), new Card(SPADE, ACE)), 21),
                Arguments.arguments(List.of(new Card(DIAMOND, KING), new Card(DIAMOND, QUEEN), new Card(DIAMOND, ACE)), 21),
                Arguments.arguments(List.of(new Card(DIAMOND, ACE), new Card(CLOVER, ACE), new Card(SPADE, ACE)), 13)
        );
    }
}
