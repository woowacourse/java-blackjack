package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Number.JACK;
import static blackjack.domain.Number.QUEEN;
import static blackjack.domain.Number.TEN;
import static blackjack.domain.Number.TWO;
import static blackjack.domain.Suit.CLOVER;
import static blackjack.domain.Suit.HEART;
import static blackjack.domain.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    private static Stream<Arguments> generateCardsWithoutACE() {
        return Stream.of(
                Arguments.of(List.of(new Card(TWO, SPADE), new Card(JACK, HEART)), 12),
                Arguments.of(List.of(), 0)
        );
    }

    private static Stream<Arguments> generateCardsWithACE() {
        return Stream.of(
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(JACK, HEART)), 21),
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(ACE, HEART), new Card(TEN, CLOVER)), 12),
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(QUEEN, HEART), new Card(TEN, CLOVER)), 21),
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(ACE, HEART)), 12)
        );
    }

    @ParameterizedTest
    @MethodSource("generateCardsWithoutACE")
    void 점수를_계산한다(final List<Card> cardPack, final int totalScore) {
        final Cards cards = new Cards(cardPack);

        assertThat(cards.calculateTotalScore()).isEqualTo(totalScore);
    }

    @ParameterizedTest
    @MethodSource("generateCardsWithACE")
    void 에이스가_포함된_경우_최적의_점수를_계산한다(final List<Card> cardPack, final int totalScore) {
        final Cards cards = new Cards(cardPack);

        assertThat(cards.calculateTotalScore()).isEqualTo(totalScore);
    }
}

class Cards {

    private static final int MAXIMUM_SCORE = 21;
    private static final int ACE_BONUS = 10;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public int calculateTotalScore() {
        final int score = getTotalScore();

        if (isExistAce() && isScoreUpdatable(score)) {
            return score + ACE_BONUS;
        }

        return score;
    }

    private int getTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean isExistAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean isScoreUpdatable(final int score) {
        return score + ACE_BONUS <= MAXIMUM_SCORE;
    }
}
