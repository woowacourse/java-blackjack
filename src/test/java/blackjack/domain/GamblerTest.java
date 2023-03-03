package blackjack.domain;

import static blackjack.domain.Rank.ACE;
import static blackjack.domain.Rank.FOUR;
import static blackjack.domain.Rank.JACK;
import static blackjack.domain.Rank.SEVEN;
import static blackjack.domain.Rank.SIX;
import static blackjack.domain.Rank.THREE;
import static blackjack.domain.Shape.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

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
        final Gambler gambler = new Gambler("허브");

        assertThat(gambler.getName()).isEqualTo("허브");
    }

    @Test
    void 게임_시작_시_카드를_뽑는다() {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(List.of(
                new Card(ACE, Shape.DIAMOND),
                new Card(JACK, Shape.DIAMOND)
        ));

        gambler.initialDraw(deck);

        assertThat(gambler.getCardLetters()).containsExactly("A다이아몬드", "J다이아몬드");
    }

    @ParameterizedTest(name = "카드를 뽑을 수 있는지 확인한다. 입력: {0}, 결과: {1}")
    @MethodSource("isDrawableSource")
    void 카드를_뽑을_수_있는지_확인한다(final List<Card> cards, final boolean result) {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(cards);
        gambler.initialDraw(deck);
        gambler.draw(deck);

        assertThat(gambler.isDrawable()).isEqualTo(result);
    }

    static Stream<Arguments> isDrawableSource() {
        return Stream.of(
                Arguments.of(List.of(new Card(JACK, CLOVER), new Card(SIX, CLOVER), new Card(SIX, CLOVER)), false),
                Arguments.of(List.of(new Card(JACK, CLOVER), new Card(SEVEN, CLOVER), new Card(FOUR, CLOVER)), false),
                Arguments.of(List.of(new Card(JACK, CLOVER), new Card(SEVEN, CLOVER), new Card(THREE, CLOVER)), true)
        );
    }

    @Test
    void 카드를_뽑는다() {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(List.of(
                new Card(ACE, Shape.DIAMOND)
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
                new Card(ACE, Shape.DIAMOND)
        ));
        gambler.draw(deck);

        assertThat(gambler.calculateScore()).isEqualTo(11);
    }
}
