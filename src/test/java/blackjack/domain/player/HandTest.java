package blackjack.domain.player;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.player.Result.*;
import static blackjack.util.CardFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class HandTest {

    @Test
    void 카드를_넣는다() {
        final Hand hand = new Hand();

        hand.add(ACE_SPADE);

        assertThat(hand.getCardLetters()).containsExactly("A스페이드");
    }

    @ParameterizedTest(name = "승패를 구한다. 갬블러: {0}, 딜러: {1}, 결과값: {2}")
    @MethodSource("compareHandSource")
    void 승패를_구한다(final List<Card> cards, final List<Card> dealerCards, final Result expectedResult) {
        final Hand hand = generateHand(cards);
        final Hand dealerHand = generateHand(dealerCards);

        final Result result = hand.compare(dealerHand);

        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> compareHandSource() {
        return Stream.of(
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), List.of(KING_SPADE, QUEEN_SPADE), BLACKJACK),
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), List.of(QUEEN_SPADE, SIX_SPADE, TEN_SPADE), BLACKJACK),
                Arguments.of(List.of(JACK_SPADE, KING_SPADE), List.of(QUEEN_SPADE, EIGHT_SPADE), WIN),
                Arguments.of(List.of(JACK_SPADE, SEVEN_SPADE), List.of(KING_SPADE, SIX_SPADE, QUEEN_SPADE), WIN),
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), List.of(ACE_DIAMOND, KING_SPADE), PUSH),
                Arguments.of(List.of(JACK_SPADE, SIX_SPADE, KING_SPADE), List.of(QUEEN_SPADE, FIVE_SPADE, TEN_SPADE), PUSH),
                Arguments.of(List.of(JACK_SPADE, KING_SPADE), List.of(QUEEN_SPADE, TEN_SPADE), PUSH),
                Arguments.of(List.of(JACK_SPADE, TEN_SPADE), List.of(KING_SPADE, ACE_SPADE), LOSE),
                Arguments.of(List.of(JACK_SPADE, KING_SPADE, QUEEN_SPADE), List.of(TEN_SPADE, ACE_SPADE), LOSE),
                Arguments.of(List.of(JACK_SPADE, SEVEN_SPADE), List.of(QUEEN_SPADE, TEN_SPADE), LOSE),
                Arguments.of(List.of(JACK_SPADE, SEVEN_SPADE, KING_SPADE), List.of(QUEEN_SPADE, SIX_SPADE), LOSE)
        );
    }

    private Hand generateHand(List<Card> testCards) {
        final Hand hand = new Hand();
        for (Card card : testCards) {
            hand.add(card);
        }
        return hand;
    }

    @ParameterizedTest(name = "카드를 뽑을 수 있는지 확인한다. 입력값: {0}, 결과값: {1}")
    @MethodSource("isPlayableSource")
    void 카드를_뽑을_수_있는지_확인한다(final List<Card> cards, final boolean result) {
        final Hand hand = generateHand(cards);

        assertThat(hand.isPlayable()).isEqualTo(result);
    }

    static Stream<Arguments> isPlayableSource() {
        return Stream.of(
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), false),
                Arguments.of(List.of(JACK_SPADE, KING_SPADE, TWO_SPADE), false),
                Arguments.of(List.of(KING_SPADE, JACK_SPADE), true)
        );
    }

    @Test
    void 점수_산정에_들어갈_결과값을_반환한다() {
        final Hand hand = new Hand();
        hand.add(ACE_HEART);

        assertThat(hand.calculateScore()).isEqualTo(11);
    }

    @Test
    void 상태를_STAY로_바꾼다() {
        final Hand hand = new Hand();

        hand.stay();

        assertThat(hand.isPlayable()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("isBlackjackSource")
    void 블랙잭인지_확인한다(final List<Card> cards, final boolean expected) {
        final Hand hand = generateHand(cards);

        final boolean actual = hand.isBlackjack();

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> isBlackjackSource() {
        return Stream.of(
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), true),
                Arguments.of(List.of(ACE_SPADE, SEVEN_SPADE), false)
        );
    }

    @ParameterizedTest
    @MethodSource("isBustSource")
    void 버스트인지_확인한다(final List<Card> cards, final boolean expected) {
        final Hand hand = generateHand(cards);

        final boolean actual = hand.isBust();

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> isBustSource() {
        return Stream.of(
                Arguments.of(List.of(JACK_SPADE, KING_SPADE, TWO_SPADE), true),
                Arguments.of(List.of(JACK_SPADE, QUEEN_SPADE, ACE_SPADE), false),
                Arguments.of(List.of(JACK_SPADE, ACE_SPADE), false)
        );
    }
}
