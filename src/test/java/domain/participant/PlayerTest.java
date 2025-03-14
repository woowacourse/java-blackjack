package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardValue;
import domain.card.Deck;
import domain.card.Suit;
import domain.card.TrumpCard;
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
        Deck deck = BlackjackDeckTestFixture.createSequentialDeck(hand);
        Player player = new Player(ParticipantName.nameOf("루키"));

        // when
        int cardCount = hand.size();
        for (int i = 0; i < cardCount; i++) {
            player.addCard(deck.drawCard());
        }

        // then
        boolean result = player.isDrawable();
        assertThat(result).isTrue();
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
        Deck deck = BlackjackDeckTestFixture.createSequentialDeck(hand);
        Player player = new Player(ParticipantName.nameOf("루키"));

        // when
        int cardCount = hand.size();
        for (int i = 0; i < cardCount; i++) {
            player.addCard(deck.drawCard());
        }

        // then
        assertThat(player.isDrawable()).isFalse();
    }

    static Stream<Arguments> createDrawCard() {
        return Stream.of(
                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.TWO),
                                new TrumpCard(Suit.CLOVER, CardValue.K),
                                new TrumpCard(Suit.HEART, CardValue.K)
                        ),
                        false
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.K),
                                new TrumpCard(Suit.CLOVER, CardValue.A),
                                new TrumpCard(Suit.DIAMOND, CardValue.NINE)
                        ),
                        true
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.A)),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("createDrawCard")
    void 플레이어의_드로우_가능여부를_반환한다(List<TrumpCard> hand, boolean expected) {
        // given
        Deck deck = BlackjackDeckTestFixture.createSequentialDeck(hand);
        Player player = new Player(ParticipantName.nameOf("루키"));

        int cardCount = hand.size();
        for (int i = 0; i < cardCount; i++) {
            player.addCard(deck.drawCard());

        }

        // when
        boolean actual = player.isDrawable();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
