package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.util.CardsFixture;
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
    void 겜블러를_생성한다() {
        final Gambler gambler = Gambler.create("허브");

        assertThat(gambler.getNameValue()).isEqualTo("허브");
    }

    @ParameterizedTest(name = "카드를 뽑을 수 있는지 확인한다. 입력: {0}, 결과: {1}")
    @MethodSource("isDrawableSource")
    void 카드를_뽑을_수_있는지_확인한다(final List<Card> cards, final boolean result) {
        final Deck deck = new FixedDeck(cards);
        final Gambler gambler = Gambler.create("허브");
        gambler.initialDraw(deck);
        gambler.draw(deck);

        final boolean drawable = gambler.isDrawable();

        assertThat(drawable).isEqualTo(result);
    }

    static Stream<Arguments> isDrawableSource() {
        return Stream.of(
                Arguments.of(CardsFixture.BUST, false),
                Arguments.of(CardsFixture.valueOf(21), false),
                Arguments.of(CardsFixture.valueOf(20), true)
        );
    }

    @Test
    void 딜러가_아닌지_확인한다() {
        final Gambler gambler = Gambler.create("후추");

        assertThat(gambler.isDealer()).isFalse();
    }
}
