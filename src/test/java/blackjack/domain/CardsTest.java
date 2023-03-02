package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CardsTest {

    @ParameterizedTest(name = "점수 산정에 들어갈 결과값을 반환한다. 입력: {0}, 점수: {1}")
    @MethodSource("calculateTotalScoreSource")
    void 점수_산정에_들어갈_결과값을_반환한다(final List<Rank> ranks, final int result) {
        final Cards cards = new Cards();

        for (Rank rank : ranks) {
            cards.add(new Card(rank, Shape.SPADE));
        }

        assertThat(cards.calculateTotalScore()).isEqualTo(result);
    }

    static Stream<Arguments> calculateTotalScoreSource() {
        return Stream.of(
                Arguments.of(List.of(Rank.FIVE, Rank.ACE), 16),
                Arguments.of(List.of(Rank.ACE, Rank.ACE), 12),
                Arguments.of(List.of(Rank.ACE, Rank.KING), 21),
                Arguments.of(List.of(Rank.FOUR, Rank.ACE, Rank.NINE), 14),
                Arguments.of(List.of(Rank.JACK, Rank.ACE, Rank.KING), 21),
                Arguments.of(List.of(Rank.ACE, Rank.ACE, Rank.ACE, Rank.ACE), 14),
                Arguments.of(List.of(Rank.JACK, Rank.ACE, Rank.ACE), 12),
                Arguments.of(List.of(Rank.ACE, Rank.QUEEN, Rank.FOUR, Rank.SEVEN), 22),
                Arguments.of(List.of(Rank.JACK, Rank.ACE, Rank.KING, Rank.NINE), 30)
        );
    }

    @Test
    void 점수_최솟값을_반환한다() {
        final Cards cards = new Cards();
        cards.add(new Card(Rank.FIVE, Shape.DIAMOND));
        cards.add(new Card(Rank.ACE, Shape.CLOVER));

        assertThat(cards.calculateMinScore()).isEqualTo(6);
    }

    @Test
    void 카드를_추가한다() {
        final Cards cards = new Cards();

        cards.add(new Card(Rank.FIVE, Shape.DIAMOND));

        assertThat(cards.getCardLetters()).containsExactly("5다이아몬드");
    }

    @Test
    void 모든_카드의_정보를_반환한다() {
        final Cards cards = new Cards();
        cards.add(new Card(Rank.FIVE, Shape.DIAMOND));
        cards.add(new Card(Rank.ACE, Shape.CLOVER));

        final List<String> result = cards.getCardLetters();

        assertThat(result).containsExactly("5다이아몬드", "A클로버");
    }

    @ParameterizedTest(name = "블랙잭인지 확인한다. 입력값: {0}, 결과값: {1}")
    @MethodSource("isBlackjackSource")
    void 블랙잭인지_확인한다(final List<Rank> ranks, final boolean result) {
        final Cards cards = new Cards();

        for (Rank rank : ranks) {
            cards.add(new Card(rank, Shape.SPADE));
        }

        assertThat(cards.isBlackjack()).isEqualTo(result);
    }

    static Stream<Arguments> isBlackjackSource() {
        return Stream.of(
                Arguments.of(List.of(Rank.FIVE, Rank.ACE), false),
                Arguments.of(List.of(Rank.ACE, Rank.ACE), false),
                Arguments.of(List.of(Rank.ACE, Rank.KING), true),
                Arguments.of(List.of(Rank.FIVE, Rank.KING, Rank.SIX), false),
                Arguments.of(List.of(Rank.ACE, Rank.QUEEN, Rank.FOUR, Rank.SEVEN), false)
        );
    }
}
