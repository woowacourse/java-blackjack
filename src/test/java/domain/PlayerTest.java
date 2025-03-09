package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.fixture.BlackjackDeckTestFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    static Stream<Arguments> createDrawableCards() {
        return Stream.of(
                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                        new TrumpCard(Suit.CLOVER, CardValue.NINE)
                )),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.K),
                        new TrumpCard(Suit.CLOVER, CardValue.SEVEN)
                )),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.K),
                        new TrumpCard(Suit.CLOVER, CardValue.SEVEN),
                        new TrumpCard(Suit.CLOVER, CardValue.FOUR)
                )),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.FIVE),
                        new TrumpCard(Suit.CLOVER, CardValue.K),
                        new TrumpCard(Suit.HEART, CardValue.A)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("createDrawableCards")
    void 카드의_합이_21_이하면_뽑을_수_있다(List<TrumpCard> hand) {
        // given
        BlackjackDeck deck = BlackjackDeckTestFixture.createSequentialDeck(hand);
        Player player = new Player("루키");

        // when
        int cardCount = hand.size();
        for (int i = 0; i < cardCount; i++) {
            player.addDraw(deck.drawCard());
        }

        // then
        assertThat(player.isDrawable()).isTrue();
    }

    static Stream<Arguments> createBustCards() {
        return Stream.of(
                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                        new TrumpCard(Suit.CLOVER, CardValue.NINE),
                        new TrumpCard(Suit.CLOVER, CardValue.FIVE)
                )),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.K),
                        new TrumpCard(Suit.CLOVER, CardValue.SEVEN),
                        new TrumpCard(Suit.DIAMOND, CardValue.FIVE)
                )),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.FIVE),
                        new TrumpCard(Suit.CLOVER, CardValue.K),
                        new TrumpCard(Suit.HEART, CardValue.SEVEN)))
        );
    }

    @ParameterizedTest
    @MethodSource("createBustCards")
    void 카드의_합이_21_초과면_카드를_뽑을_수_없다(List<TrumpCard> hand) {
        // given
        BlackjackDeck deck = BlackjackDeckTestFixture.createSequentialDeck(hand);
        Player player = new Player("루키");

        // when
        int cardCount = hand.size();
        for (int i = 0; i < cardCount; i++) {
            player.addDraw(deck.drawCard());
        }

        // then
        assertThat(player.isDrawable()).isFalse();
    }


    static Stream<Arguments> createAceCardHand() {
        return Stream.of(
                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.K)
                        ),
                        21
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.A),
                                new TrumpCard(Suit.DIAMOND, CardValue.NINE)
                        ),
                        21
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.A)),
                        12
                )
        );
    }

    @ParameterizedTest
    @MethodSource("createAceCardHand")
    void 에이스가_포함된_경우에_에이스를_최적의_값으로_계산한다(List<TrumpCard> hand, int expected) {
        // given
        BlackjackDeck deck = BlackjackDeckTestFixture.createSequentialDeck(hand);
        Player player = new Player("루키");

        // when
        int cardCount = hand.size();
        for (int i = 0; i < cardCount; i++) {
            player.addDraw(deck.drawCard());
        }
        int score = player.calculateCardSum();

        // then
        assertThat(score).isEqualTo(expected);
    }
}
