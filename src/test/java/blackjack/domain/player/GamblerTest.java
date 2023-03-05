package blackjack.domain.player;

import static blackjack.util.CardFixtures.ACE_DIAMOND;
import static blackjack.util.CardFixtures.ACE_SPADE;
import static blackjack.util.CardFixtures.FOUR_SPADE;
import static blackjack.util.CardFixtures.JACK_CLOVER;
import static blackjack.util.CardFixtures.JACK_SPADE;
import static blackjack.util.CardFixtures.KING_HEART;
import static blackjack.util.CardFixtures.KING_SPADE;
import static blackjack.util.CardFixtures.SEVEN_SPADE;
import static blackjack.util.CardFixtures.SIX_HEART;
import static blackjack.util.CardFixtures.SIX_SPADE;
import static blackjack.util.CardFixtures.THREE_SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.util.FixedDeck;
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
public class GamblerTest {

    @Test
    void 겜블러가_정상_생성된다() {
        final Gambler gambler = Gambler.create("허브");

        assertThat(gambler.getName()).isEqualTo("허브");
    }

    @Test
    void 덱을_입력받아_딜러를_생성한다() {
        final Deck deck = new FixedDeck(ACE_SPADE, KING_HEART);

        final Gambler gambler = Gambler.create("허브", deck);

        assertAll(
                () -> assertThat(gambler.getName()).isEqualTo("허브"),
                () -> assertThat(gambler.getCardLetters()).containsExactly("A스페이드", "K하트")
        );
    }

    @Test
    void 게임_시작_시_카드를_뽑는다() {
        final Gambler gambler = Gambler.create("허브");
        final Deck deck = new FixedDeck(ACE_DIAMOND, JACK_CLOVER);

        gambler.initialDraw(deck);

        assertThat(gambler.getCardLetters()).containsExactly("A다이아몬드", "J클로버");
    }

    @ParameterizedTest(name = "카드를 뽑을 수 있는지 확인한다. 입력: {0}, 결과: {1}")
    @MethodSource("isDrawableSource")
    void 카드를_뽑을_수_있는지_확인한다(final List<Card> cards, final boolean result) {
        final Deck deck = new FixedDeck(cards);
        final Gambler gambler = Gambler.create("허브", deck);
        gambler.draw(deck);

        final boolean drawable = gambler.isDrawable();
        
        assertThat(drawable).isEqualTo(result);
    }

    static Stream<Arguments> isDrawableSource() {
        return Stream.of(
                Arguments.of(List.of(JACK_SPADE, SIX_SPADE, SIX_HEART), false),
                Arguments.of(List.of(JACK_SPADE, SEVEN_SPADE, FOUR_SPADE), false),
                Arguments.of(List.of(JACK_SPADE, SEVEN_SPADE, THREE_SPADE), true)
        );
    }

    @Test
    void 카드를_뽑는다() {
        final Gambler gambler = Gambler.create("허브");
        final Deck deck = new FixedDeck(ACE_DIAMOND);

        gambler.draw(deck);

        assertThat(gambler.getCardLetters()).containsExactly("A다이아몬드");
    }

    @Test
    void 딜러가_아닌지_확인한다() {
        final Gambler gambler = Gambler.create("후추");

        assertThat(gambler.isDealer()).isFalse();
    }

    @Test
    void 점수를_반환한다() {
        final Gambler gambler = Gambler.create("허브", new FixedDeck(ACE_DIAMOND, KING_HEART));

        assertThat(gambler.calculateScore()).isEqualTo(21);
    }

    @Test
    void 카드를_더_뽑을_수_없는_상태로_변경한다() {
        final Gambler gambler = Gambler.create("허브", new FixedDeck(ACE_DIAMOND, KING_HEART));

        gambler.stay();

        assertThat(gambler.isDrawable()).isFalse();
    }

    @Test
    void 게임의_결과를_반환한다() {
        final Gambler gambler = Gambler.create("허브", new FixedDeck(ACE_DIAMOND, KING_HEART));
        final Hand hand = new Hand(List.of(KING_SPADE));

        final Result result = gambler.play(hand);

        assertThat(result).isEqualTo(Result.WIN);
    }
}
