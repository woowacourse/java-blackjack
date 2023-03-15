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
public class DealerTest {

    @Test
    void 딜러를_생성한다() {
        final Dealer dealer = Dealer.create();

        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    void 게임_시작_시_카드를_뽑는다() {
        final Dealer dealer = Dealer.create();
        final Deck deck = new FixedDeck(List.of(
                ACE_DIAMOND,
                JACK_SPADE
        ));

        dealer.drawInitialCard(deck);

        assertThat(dealer.getCardLetters()).containsExactly("A다이아몬드", "J스페이드");
    }

    @ParameterizedTest(name = "카드를 뽑을 수 있는지 확인한다. 입력: {0}, 결과: {1}")
    @MethodSource("isDrawableSource")
    void 카드를_뽑을_수_있는지_확인한다(final List<Card> cards, final boolean result) {
        final Dealer dealer = Dealer.create();
        final Deck deck = new FixedDeck(cards);
        dealer.drawInitialCard(deck);

        assertThat(dealer.isDrawable()).isEqualTo(result);
    }

    static Stream<Arguments> isDrawableSource() {
        return Stream.of(
                Arguments.of(List.of(JACK_SPADE, FIVE_SPADE), true),
                Arguments.of(List.of(JACK_SPADE, SEVEN_SPADE), false)
        );
    }

    @Test
    void 카드를_뽑는다() {
        final Dealer dealer = Dealer.create();
        final Deck deck = new FixedDeck(List.of(
                ACE_DIAMOND,
                JACK_SPADE,
                THREE_SPADE
        ));
        dealer.drawInitialCard(deck);

        dealer.draw(deck);

        assertThat(dealer.getCardLetters()).containsExactly("A다이아몬드", "J스페이드", "3스페이드");
    }

    @Test
    void 딜러인지_확인한다() {
        final Dealer dealer = Dealer.create();

        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    void 점수를_반환한다() {
        final Dealer dealer = Dealer.create();
        final Deck deck = new FixedDeck(List.of(
                ACE_SPADE
        ));
        dealer.draw(deck);

        assertThat(dealer.calculateScore()).isEqualTo(11);
    }

    @Test
    void 상태를_STOP으로_바꾼다() {
        final Dealer dealer = Dealer.create();

        dealer.stay();

        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    void 딜러의_카드수를_반환한다() {
        final Dealer dealer = Dealer.create();
        final Deck deck = new FixedDeck(List.of(
                ACE_DIAMOND
        ));

        dealer.draw(deck);

        assertThat(dealer.getCardCount()).isEqualTo(1);
    }

    @Test
    void 핸드를_반환한다() {
        final Dealer dealer = Dealer.create();

        final Hand hand = dealer.getHand();

        assertThat(hand.calculateScore()).isEqualTo(0);
    }

    @Test
    void 첫번째_카드를_반환한다() {
        final Dealer dealer = Dealer.create();
        final Deck deck = new FixedDeck(List.of(
                ACE_DIAMOND,
                JACK_SPADE
        ));
        dealer.drawInitialCard(deck);

        final String firstCardLetter = dealer.getFirstCardLetter();

        assertThat(firstCardLetter).isEqualTo("A다이아몬드");
    }


    @ParameterizedTest
    @MethodSource("isBlackjackSource")
    void 딜러_카드가_블랙잭인지_확인한다(final List<Card> cards, final boolean expectedResult) {
        final Dealer dealer = Dealer.create();
        final Deck deck = new FixedDeck(cards);
        dealer.drawInitialCard(deck);

        assertThat(dealer.isBlackjack()).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> isBlackjackSource() {
        return Stream.of(
                Arguments.of(List.of(JACK_SPADE, SIX_SPADE), false),
                Arguments.of(List.of(JACK_SPADE, ACE_SPADE), true)
        );
    }

    @ParameterizedTest
    @MethodSource("isBustSource")
    void 딜러_카드가_버스트인지_확인한다(final List<Card> cards, final boolean expectedResult) {
        final Dealer dealer = Dealer.create();
        final Deck deck = new FixedDeck(cards);
        dealer.drawInitialCard(deck);
        dealer.draw(deck);

        assertThat(dealer.isBust()).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> isBustSource() {
        return Stream.of(
                Arguments.of(List.of(JACK_SPADE, EIGHT_SPADE, TWO_SPADE), false),
                Arguments.of(List.of(JACK_SPADE, QUEEN_SPADE, KING_SPADE), true)
        );
    }
}

