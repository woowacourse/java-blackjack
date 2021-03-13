package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static blackjack.domain.Fixture.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardsTest {
    Cards cards;

    @BeforeEach
    private void setUp() {
        this.cards = new Cards();
    }

    private static Stream<Arguments> twoCardsProvider() {
        return Stream.of(
                Arguments.of(12, new Card[]{king, two}),
                Arguments.of(13, new Card[]{king, three}),
                Arguments.of(14, new Card[]{king, four}),
                Arguments.of(15, new Card[]{king, five}),
                Arguments.of(16, new Card[]{king, six}),
                Arguments.of(17, new Card[]{king, seven}),
                Arguments.of(18, new Card[]{king, eight}),
                Arguments.of(19, new Card[]{king, nine}),
                Arguments.of(20, new Card[]{king, ten})
        );
    }

    @DisplayName("가진 패의 점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("twoCardsProvider")
    void getScoreTest(int score, Card... cardArray) {
        for (Card card : cardArray) {
            cards.draw(card);
        }

        assertThat(cards.getScore()).isEqualTo(score);
    }

    private static Stream<Arguments> cardsProvider() {
        return Stream.of(
                Arguments.of(21, new Card[]{king, ace}),
                Arguments.of(13, new Card[]{king, two, ace}),
                Arguments.of(14, new Card[]{king, three, ace}),
                Arguments.of(15, new Card[]{king, four, ace}),
                Arguments.of(16, new Card[]{king, five, ace}),
                Arguments.of(16, new Card[]{two, three, ace}),
                Arguments.of(17, new Card[]{two, four, ace}),
                Arguments.of(20, new Card[]{four, five, ace}),
                Arguments.of(12, new Card[]{five, six, ace})
        );
    }

    @DisplayName("Ace가 섞여 있을 때 최선의 점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("cardsProvider")
    void getMaximunScoreTest(int score, Card... cardArray) {
        for (Card card : cardArray) {
            cards.draw(card);
        }

        assertThat(cards.getScore()).isEqualTo(score);
    }

    @DisplayName("블랙잭 여부를 판별할 수 있다.")
    @Test
    void isBlackJackTest() {
        cards.draw(king);
        cards.draw(ace);

        assertTrue(cards.isBlackJack());
    }

    @DisplayName("버스트 여부를 판별할 수 있다.")
    @Test
    void isBustTest() {
        cards.draw(king);
        cards.draw(queen);
        cards.draw(two);

        assertTrue(cards.isBust());
    }
}
