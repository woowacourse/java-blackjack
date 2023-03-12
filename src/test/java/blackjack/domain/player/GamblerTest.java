package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.util.FixedDeck;
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
public class GamblerTest {

    @Test
    void 겜블러가_정상_생성된다() {
        final Gambler gambler = new Gambler("허브");

        assertThat(gambler.getName()).isEqualTo("허브");
    }

    @Test
    void 게임_시작_시_카드를_뽑는다() {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(List.of(
                ACE_DIAMOND,
                JACK_SPADE
        ));

        gambler.drawInitialCard(deck);

        assertThat(gambler.getCardLetters()).containsExactly("A다이아몬드", "J스페이드");
    }

    @ParameterizedTest(name = "카드를 뽑을 수 있는지 확인한다. 입력: {0}, 결과: {1}")
    @MethodSource("isDrawableSource")
    void 카드를_뽑을_수_있는지_확인한다(final List<Card> cards, final boolean result) {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(cards);
        gambler.drawInitialCard(deck);
        gambler.draw(deck);

        assertThat(gambler.isDrawable()).isEqualTo(result);
    }

    static Stream<Arguments> isDrawableSource() {
        return Stream.of(
                Arguments.of(List.of(JACK_SPADE, SIX_SPADE, SEVEN_SPADE), false),
                Arguments.of(List.of(JACK_SPADE, SEVEN_SPADE, FOUR_SPADE), false),
                Arguments.of(List.of(JACK_SPADE, SEVEN_SPADE, THREE_SPADE), true)
        );
    }

    @Test
    void 카드를_뽑는다() {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(List.of(
                ACE_DIAMOND
        ));

        gambler.draw(deck);

        assertThat(gambler.getCardLetters()).containsExactly("A다이아몬드");
    }

    @Test
    void 딜러가_아닌지_확인한다() {
        final Gambler gambler = new Gambler("후추");

        assertThat(gambler.isDealer()).isFalse();
    }

    @Test
    void 점수를_반환한다() {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(List.of(
                ACE_SPADE
        ));
        gambler.draw(deck);

        assertThat(gambler.calculateScore()).isEqualTo(11);
    }

    @Test
    void 상태를_STOP으로_바꾼다() {
        final Gambler gambler = new Gambler("허브");

        gambler.stay();

        assertThat(gambler.isDrawable()).isFalse();
    }

    @Test
    void 게임의_결과를_반환한다() {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(List.of(
                ACE_DIAMOND,
                KING_SPADE
        ));
        gambler.draw(deck);
        final Dealer dealer = Dealer.create();
        dealer.draw(deck);

        final Result result = gambler.compareHandTo(dealer.getHand());

        assertThat(result).isEqualTo(Result.WIN);
    }

    @ParameterizedTest
    @MethodSource("isBlackjackSource")
    void 겜블러_카드가_블랙잭인지_확인한다(final List<Card> cards, final boolean expectedResult) {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(cards);
        gambler.drawInitialCard(deck);

        assertThat(gambler.isBlackjack()).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> isBlackjackSource() {
        return Stream.of(
                Arguments.of(List.of(JACK_SPADE, SIX_SPADE), false),
                Arguments.of(List.of(JACK_SPADE, ACE_SPADE), true)
        );
    }

    @ParameterizedTest
    @MethodSource("isBustSource")
    void 겜블러_카드가_버스트인지_확인한다(final List<Card> cards, final boolean expectedResult) {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(cards);
        gambler.drawInitialCard(deck);
        gambler.draw(deck);

        assertThat(gambler.isBust()).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> isBustSource() {
        return Stream.of(
                Arguments.of(List.of(JACK_SPADE, QUEEN_SPADE, ACE_SPADE), false),
                Arguments.of(List.of(JACK_SPADE, QUEEN_SPADE, KING_SPADE), true)
        );
    }
}
