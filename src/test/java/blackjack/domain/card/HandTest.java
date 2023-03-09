package blackjack.domain.card;

import static blackjack.domain.card.Rank.ACE;
import static blackjack.domain.card.Shape.HEART;
import static blackjack.domain.player.Result.BLACKJACK_WIN;
import static blackjack.domain.player.Result.LOSE;
import static blackjack.domain.player.Result.PUSH;
import static blackjack.domain.player.Result.WIN;
import static blackjack.util.CardFixtures.ACE_SPADE;
import static blackjack.util.CardFixtures.EIGHT_SPADE;
import static blackjack.util.CardFixtures.JACK_CLOVER;
import static blackjack.util.CardFixtures.JACK_HEART;
import static blackjack.util.CardFixtures.JACK_SPADE;
import static blackjack.util.CardFixtures.KING_SPADE;
import static blackjack.util.CardFixtures.SEVEN_SPADE;
import static blackjack.util.CardFixtures.SIX_SPADE;
import static blackjack.util.CardFixtures.TWO_SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.Result;
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
public class HandTest {

    @Test
    void 카드를_입력받아_핸드를_생성한다() {
        final List<Card> cards = List.of(ACE_SPADE, JACK_SPADE);

        final Hand hand = new Hand(cards);

        assertThat(hand.score()).isEqualTo(21);
    }

    @ParameterizedTest(name = "승패를 구한다. 갬블러: {0}, 딜러: {1}, 결과값: {2}")
    @MethodSource("playBlackjackSource")
    void 승패를_구한다(final List<Card> cards, final List<Card> dealerCards, final Result expectedResult) {
        final Hand hand = new Hand(cards);
        final Hand dealerHand = new Hand(dealerCards);

        final Result result = hand.play(dealerHand);

        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> playBlackjackSource() {
        return Stream.of(
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), List.of(ACE_SPADE, JACK_SPADE), PUSH),
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), List.of(JACK_SPADE, JACK_HEART), BLACKJACK_WIN),
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), List.of(JACK_SPADE, SIX_SPADE, JACK_HEART), BLACKJACK_WIN),
                Arguments.of(List.of(JACK_SPADE, JACK_HEART), List.of(JACK_SPADE, ACE_SPADE), LOSE),
                Arguments.of(List.of(JACK_SPADE, JACK_HEART, JACK_CLOVER), List.of(JACK_SPADE, ACE_SPADE), LOSE),
                Arguments.of(List.of(JACK_SPADE, JACK_HEART), List.of(JACK_SPADE, EIGHT_SPADE), WIN),
                Arguments.of(List.of(JACK_SPADE, JACK_HEART), List.of(JACK_SPADE, JACK_HEART), PUSH),
                Arguments.of(List.of(JACK_SPADE, SEVEN_SPADE), List.of(JACK_SPADE, JACK_HEART), LOSE),
                Arguments.of(List.of(JACK_SPADE, SEVEN_SPADE), List.of(JACK_SPADE, SIX_SPADE, JACK_HEART), WIN),
                Arguments.of(List.of(JACK_SPADE, SEVEN_SPADE, KING_SPADE), List.of(JACK_SPADE, SEVEN_SPADE), LOSE),
                Arguments.of(List.of(JACK_SPADE, SIX_SPADE, KING_SPADE), List.of(JACK_SPADE, SIX_SPADE, KING_SPADE),
                        LOSE)
        );
    }

    @Test
    void 카드가_추가된다() {
        final Hand hand = new Hand();

        hand.add(new Card(ACE, HEART));

        assertThat(hand.getSymbols()).containsExactly("A하트");
    }

    @ParameterizedTest(name = "카드를 뽑을 수 있는지 확인한다. 입력값: {0}, 결과값: {1}")
    @MethodSource("isPlayableSource")
    void 카드를_뽑을_수_있는지_확인한다(final List<Card> cards, final boolean result) {
        final Hand hand = new Hand(cards);

        assertThat(hand.isPlayable()).isEqualTo(result);
    }

    static Stream<Arguments> isPlayableSource() {
        return Stream.of(
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), false),
                Arguments.of(List.of(JACK_SPADE, JACK_HEART, TWO_SPADE), false),
                Arguments.of(List.of(JACK_SPADE, JACK_HEART), true)
        );
    }

    @Test
    void 점수_산정에_들어갈_결과값을_반환한다() {
        final Hand hand = new Hand(List.of(ACE_SPADE));

        final int result = hand.score();

        assertThat(result).isEqualTo(11);
    }

    @Test
    void 카드를_더_뽑을_수_없는_상태로_변경한다() {
        final Hand hand = new Hand(List.of(ACE_SPADE));

        hand.stay();

        assertThat(hand.isPlayable()).isFalse();
    }
}
