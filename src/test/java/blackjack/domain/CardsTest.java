package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Number.JACK;
import static blackjack.domain.Number.QUEEN;
import static blackjack.domain.Number.TEN;
import static blackjack.domain.Number.TWO;
import static blackjack.domain.Suit.CLOVER;
import static blackjack.domain.Suit.DIAMOND;
import static blackjack.domain.Suit.HEART;
import static blackjack.domain.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
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

    @Test
    void 점수_최댓값_초과라면_true_반환한다() {
        final Cards cards = new Cards(
                List.of(new Card(TEN, SPADE),
                        new Card(JACK, HEART),
                        new Card(QUEEN, CLOVER)
                ));

        assertThat(cards.isTotalScoreOver()).isTrue();
    }

    @Test
    void 점수_최댓값_이하라면_false_반환한다() {
        final Cards cards = new Cards(
                List.of(new Card(TEN, SPADE),
                        new Card(JACK, HEART)
                ));

        assertThat(cards.isTotalScoreOver()).isFalse();
    }

    @Test
    void 최대_점수라면_true_반환한다() {
        final Cards cards = new Cards(
                List.of(new Card(TEN, SPADE),
                        new Card(JACK, HEART),
                        new Card(ACE, CLOVER)
                ));

        assertThat(cards.isMaximumScore()).isTrue();
    }

    @Test
    void 최대_점수_아니라면_false_반환한다() {
        final Cards cards = new Cards(
                List.of(new Card(TEN, SPADE),
                        new Card(JACK, HEART)
                ));

        assertThat(cards.isMaximumScore()).isFalse();
    }

    @Test
    void 카드를_추가한다() {
        final Cards cards = new Cards(new ArrayList<>());

        final Card card = new Card(ACE, DIAMOND);
        cards.addCard(card);

        assertThat(cards.count()).isEqualTo(1);
    }
}
