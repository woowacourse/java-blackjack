package blackjack.domain.card;

import blackjack.domain.player.Cards;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.util.CardFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CardsTest {

    @ParameterizedTest(name = "점수 산정에 들어갈 결과값을 반환한다. 입력: {0}, 점수: {1}")
    @MethodSource("calculateTotalScoreSource")
    void 점수_산정에_들어갈_결과값을_반환한다(final List<Card> testCards, final int result) {
        final Cards cards = new Cards();

        for (Card card : testCards) {
            cards.add(card);
        }

        assertThat(cards.calculateTotalScore()).isEqualTo(result);
    }

    static Stream<Arguments> calculateTotalScoreSource() {
        return Stream.of(
                Arguments.of(List.of(FIVE_SPADE, ACE_SPADE), 16),
                Arguments.of(List.of(ACE_SPADE, ACE_DIAMOND), 12),
                Arguments.of(List.of(ACE_SPADE, TEN_SPADE), 21),
                Arguments.of(List.of(FOUR_SPADE, ACE_SPADE, NINE_SPADE), 14),
                Arguments.of(List.of(JACK_SPADE, ACE_SPADE, TEN_SPADE), 21),
                Arguments.of(List.of(ACE_SPADE, ACE_CLOVER, ACE_DIAMOND, ACE_HEART), 14),
                Arguments.of(List.of(JACK_SPADE, ACE_SPADE, ACE_DIAMOND), 12),
                Arguments.of(List.of(ACE_SPADE, QUEEN_SPADE, FOUR_SPADE, SEVEN_SPADE), 22),
                Arguments.of(List.of(JACK_SPADE, ACE_SPADE, TEN_SPADE, NINE_SPADE), 30)
        );
    }

    @Test
    void 카드를_추가한다() {
        final Cards cards = new Cards();

        cards.add(FIVE_SPADE);

        assertThat(cards.getCardLetters()).containsExactly("5스페이드");
    }

    @Test
    void 모든_카드의_정보를_반환한다() {
        final Cards cards = new Cards();
        cards.add(TWO_SPADE);
        cards.add(THREE_SPADE);

        final List<String> result = cards.getCardLetters();

        assertThat(result).containsExactly("2스페이드", "3스페이드");
    }

    @ParameterizedTest(name = "블랙잭인지 확인한다. 입력값: {0}, 결과값: {1}")
    @MethodSource("isBlackjackSource")
    void 블랙잭인지_확인한다(final List<Card> testCards, final boolean result) {
        final Cards cards = new Cards();

        for (Card card : testCards) {
            cards.add(card);
        }

        assertThat(cards.isBlackjack()).isEqualTo(result);
    }

    static Stream<Arguments> isBlackjackSource() {
        return Stream.of(
                Arguments.of(List.of(FIVE_SPADE, ACE_CLOVER), false),
                Arguments.of(List.of(ACE_SPADE, ACE_HEART), false),
                Arguments.of(List.of(ACE_DIAMOND, TEN_SPADE), true),
                Arguments.of(List.of(FIVE_SPADE, KING_SPADE, SIX_SPADE), false),
                Arguments.of(List.of(ACE_DIAMOND, QUEEN_SPADE, FOUR_SPADE, SEVEN_SPADE), false)
        );
    }

    @ParameterizedTest(name = "버스트인지 확인한다. 입력값: {0}, 결과값: {1}")
    @MethodSource("isBustSource")
    void 버스트인지_확인한다(final List<Card> testCards, final boolean result) {
        final Cards cards = new Cards();

        for (Card card : testCards) {
            cards.add(card);
        }

        assertThat(cards.isBust()).isEqualTo(result);
    }

    static Stream<Arguments> isBustSource() {
        return Stream.of(
                Arguments.of(List.of(EIGHT_SPADE, ACE_HEART), false),
                Arguments.of(List.of(ACE_DIAMOND, ACE_SPADE), false),
                Arguments.of(List.of(ACE_HEART, KING_SPADE), false),
                Arguments.of(List.of(ACE_SPADE, QUEEN_SPADE, FOUR_SPADE, SEVEN_SPADE), true)
        );
    }

    @ParameterizedTest(name = "결과값이 블랙잭 점수인지 확인한다. 입력값: {0}, 결과값: {1}")
    @MethodSource("isBlackjackScoreSource")
    void 결과값이_블랙잭_점수인지_확인한다(final List<Card> testCards, final boolean result) {
        final Cards cards = new Cards();

        for (Card card : testCards) {
            cards.add(card);
        }

        assertThat(cards.isBlackjackScore()).isEqualTo(result);
    }

    static Stream<Arguments> isBlackjackScoreSource() {
        return Stream.of(
                Arguments.of(List.of(SIX_SPADE, ACE_SPADE, FOUR_SPADE), true),
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), false),
                Arguments.of(List.of(ACE_SPADE, EIGHT_SPADE), false),
                Arguments.of(List.of(KING_SPADE, QUEEN_SPADE, JACK_SPADE), false)
        );
    }
}
