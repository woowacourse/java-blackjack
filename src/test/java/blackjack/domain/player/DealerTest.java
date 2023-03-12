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
public class DealerTest {

    static Stream<Arguments> isDrawableSource() {
        return Stream.of(
                Arguments.of(CardsFixture.valueOf(16), true),
                Arguments.of(CardsFixture.valueOf(17), false)
        );
    }

    @Test
    void 딜러를_생성한다() {
        final Dealer dealer = Dealer.create();

        assertThat(dealer.getNameValue()).isEqualTo("딜러");
    }

    @ParameterizedTest(name = "카드를 뽑을 수 있는지 확인한다. 입력: {0}, 결과: {1}")
    @MethodSource("isDrawableSource")
    void 카드를_뽑을_수_있는지_확인한다(final List<Card> cards, final boolean result) {
        final Deck deck = new FixedDeck(cards);
        final Dealer dealer = Dealer.create();
        dealer.initialDraw(deck);

        assertThat(dealer.isDrawable()).isEqualTo(result);
    }

    @Test
    void 딜러인지_확인한다() {
        final Dealer dealer = Dealer.create();

        assertThat(dealer.isDealer()).isTrue();
    }
}

