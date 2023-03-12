package blackjack.domain.card;

import static blackjack.util.CardFixture.ACE_CLOVER;
import static blackjack.util.CardFixture.ACE_DIAMOND;
import static blackjack.util.CardFixture.ACE_HEART;
import static blackjack.util.CardFixture.ACE_SPADE;
import static blackjack.util.CardFixture.EIGHT_SPADE;
import static blackjack.util.CardFixture.FIVE_SPADE;
import static blackjack.util.CardFixture.FOUR_SPADE;
import static blackjack.util.CardFixture.JACK_SPADE;
import static blackjack.util.CardFixture.KING_HEART;
import static blackjack.util.CardFixture.KING_SPADE;
import static blackjack.util.CardFixture.NINE_SPADE;
import static blackjack.util.CardFixture.QUEEN_SPADE;
import static blackjack.util.CardFixture.SEVEN_SPADE;
import static blackjack.util.CardFixture.SIX_SPADE;
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
    @MethodSource("totalScoreSource")
    void 점수_산정에_들어갈_결과값을_반환한다(final List<Card> cards, final int result) {
        final Cards sut = new Cards(cards);

        assertThat(sut.totalScore()).isEqualTo(result);
    }

    static Stream<Arguments> totalScoreSource() {
        return Stream.of(
                Arguments.of(List.of(FIVE_SPADE, ACE_SPADE), 16),
                Arguments.of(List.of(ACE_SPADE, ACE_HEART), 12),
                Arguments.of(List.of(ACE_SPADE, KING_SPADE), 21),
                Arguments.of(List.of(FOUR_SPADE, ACE_SPADE, NINE_SPADE), 14),
                Arguments.of(List.of(JACK_SPADE, ACE_SPADE, KING_SPADE), 21),
                Arguments.of(List.of(ACE_SPADE, ACE_HEART, ACE_CLOVER, ACE_DIAMOND), 14),
                Arguments.of(List.of(JACK_SPADE, ACE_SPADE, ACE_HEART), 12),
                Arguments.of(List.of(ACE_SPADE, QUEEN_SPADE, FOUR_SPADE, SEVEN_SPADE), 22),
                Arguments.of(List.of(JACK_SPADE, ACE_SPADE, KING_SPADE, NINE_SPADE), 30)
        );
    }

    @Test
    void 점수_최솟값을_반환한다() {
        final Cards cards = new Cards(List.of(FIVE_SPADE, ACE_SPADE));

        assertThat(cards.minScore()).isEqualTo(6);
    }

    @Test
    void 카드가_추가된다() {
        final Cards cards = new Cards(List.of(FIVE_SPADE));

        cards.add(ACE_SPADE);

        assertThat(cards.getSymbols()).containsExactly("5스페이드", "A스페이드");
    }

    @Test
    void 모든_카드의_심볼을_반환한다() {
        final Cards cards = new Cards(List.of(FIVE_SPADE, ACE_CLOVER));

        final List<String> result = cards.getSymbols();

        assertThat(result).containsExactly("5스페이드", "A클로버");
    }

    @ParameterizedTest(name = "블랙잭인지 확인한다. 입력: {0}, 결과: {1}")
    @MethodSource("isBlackjackSource")
    void 블랙잭인지_확인한다(final List<Card> cards, final boolean result) {
        final Cards sut = new Cards(cards);

        assertThat(sut.isBlackjack()).isEqualTo(result);
    }

    static Stream<Arguments> isBlackjackSource() {
        return Stream.of(
                Arguments.of(List.of(FIVE_SPADE, ACE_SPADE), false),
                Arguments.of(List.of(ACE_SPADE, ACE_HEART), false),
                Arguments.of(List.of(ACE_SPADE, KING_SPADE), true),
                Arguments.of(List.of(FIVE_SPADE, KING_SPADE, SIX_SPADE), false),
                Arguments.of(List.of(ACE_SPADE, QUEEN_SPADE, FOUR_SPADE, SEVEN_SPADE), false)
        );
    }

    @ParameterizedTest(name = "버스트인지 확인한다. 입력: {0}, 결과: {1}")
    @MethodSource("isBustSource")
    void 버스트인지_확인한다(final List<Card> cards, final boolean result) {
        final Cards sut = new Cards(cards);

        assertThat(sut.isBust()).isEqualTo(result);
    }

    static Stream<Arguments> isBustSource() {
        return Stream.of(
                Arguments.of(List.of(FIVE_SPADE, ACE_SPADE), false),
                Arguments.of(List.of(ACE_SPADE, ACE_HEART), false),
                Arguments.of(List.of(ACE_SPADE, KING_SPADE), false),
                Arguments.of(List.of(ACE_SPADE, QUEEN_SPADE, FOUR_SPADE, SEVEN_SPADE), true)
        );
    }

    @ParameterizedTest(name = "결과값이 블랙잭 점수인지 확인한다. 입력: {0}, 결과: {1}")
    @MethodSource("isBlackjackScoreSource")
    void 결과값이_블랙잭_점수인지_확인한다(final List<Card> cards, final boolean result) {
        final Cards sut = new Cards(cards);

        assertThat(sut.isBlackjackScore()).isEqualTo(result);
    }

    static Stream<Arguments> isBlackjackScoreSource() {
        return Stream.of(
                Arguments.of(List.of(FIVE_SPADE, ACE_SPADE, FIVE_SPADE), true),
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), false),
                Arguments.of(List.of(ACE_SPADE, ACE_HEART, EIGHT_SPADE), false),
                Arguments.of(List.of(KING_SPADE, KING_HEART, JACK_SPADE), false)
        );
    }
}
