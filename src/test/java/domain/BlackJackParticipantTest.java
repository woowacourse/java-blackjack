package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.fixture.BlackjackDeckTestFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackJackParticipantTest {

    static Stream<Arguments> createAceCardHand() {
        return Stream.of(
                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.K)
                        ),
                        Score.from(21)
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.A),
                                new TrumpCard(Suit.DIAMOND, CardValue.NINE)
                        ),
                        Score.from(21)
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.A)),
                        Score.from(12)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("createAceCardHand")
    void 에이스가_포함된_경우에_에이스를_최적의_값으로_계산한다(List<TrumpCard> hand, Score expected) {
        // given
        BlackjackDeck deck = BlackjackDeckTestFixture.createSequentialDeck(hand);
        Player player = new Player("루키");

        // when
        int cardCount = hand.size();
        for (int i = 0; i < cardCount; i++) {
            player.addDraw(deck.drawCard());
        }
        Score score = player.calculateCardSum();

        // then
        assertThat(score).isEqualTo(expected);
    }
}
