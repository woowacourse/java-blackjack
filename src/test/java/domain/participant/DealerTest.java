package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardValue;
import domain.card.Suit;
import domain.card.TrumpCard;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    static Stream<Arguments> createCardsUpperThan16() {
        return Stream.of(
                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                        new TrumpCard(Suit.CLOVER, CardValue.NINE))),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.K),
                        new TrumpCard(Suit.CLOVER, CardValue.SEVEN))),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                        new TrumpCard(Suit.HEART, CardValue.SIX),
                        new TrumpCard(Suit.CLOVER, CardValue.K)))
        );
    }

    @ParameterizedTest
    @MethodSource("createCardsUpperThan16")
    void 카드의_합이_16_초과면_뽑을수_없다(List<TrumpCard> hand) {
        // given
        Dealer dealer = new Dealer(new ArrayList<>());
        for (TrumpCard drawCard : hand) {
            dealer.addCard(drawCard);
        }

        // when
        boolean actual = dealer.isDrawable();

        // then
        assertThat(actual).isFalse();
    }

    static Stream<Arguments> createCards16OrLess() {
        return Stream.of(
                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                        new TrumpCard(Suit.CLOVER, CardValue.EIGHT))),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.K),
                        new TrumpCard(Suit.CLOVER, CardValue.SIX))),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.TWO),
                        new TrumpCard(Suit.CLOVER, CardValue.K)))
        );
    }

    @ParameterizedTest
    @MethodSource("createCards16OrLess")
    void 카드의_합이_16_이하면_뽑을수_있다(List<TrumpCard> hand) {
        // given
        Dealer dealer = new Dealer(new ArrayList<>());
        for (TrumpCard drawCard : hand) {
            dealer.addCard(drawCard);
        }

        // when
        boolean actual = dealer.isDrawable();

        // then
        assertThat(actual).isTrue();
    }
}
