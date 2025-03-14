package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardValue;
import domain.card.Deck;
import domain.card.Suit;
import domain.card.TrumpCard;
import domain.fixture.BlackjackDeckTestFixture;
import domain.game.WinStatus;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantTest {

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
        Deck deck = BlackjackDeckTestFixture.createSequentialDeck(hand);
        Participant participant = new Participant(ParticipantName.nameOf("루키"));

        // when
        int cardCount = hand.size();
        for (int i = 0; i < cardCount; i++) {
            participant.addDraw(deck.drawCard());
        }
        Score score = participant.calculateCardSum();

        // then
        assertThat(score).isEqualTo(expected);
    }



    static Stream<Arguments> createScoreCard() {
        return Stream.of(
                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.K)
                        ),
                        Score.from(20),
                        WinStatus.WIN
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.A),
                                new TrumpCard(Suit.DIAMOND, CardValue.NINE)
                        ),
                        Score.from(21),
                        WinStatus.DRAW
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.A)),
                        Score.from(15),
                        WinStatus.LOSE
                )
        );
    }

    @ParameterizedTest
    @MethodSource("createScoreCard")
    void 점수를_비교하여_현재_플레이어의_승패를_반환한다(List<TrumpCard> hand, Score otherScore, WinStatus expected) {
        // given
        Deck deck = BlackjackDeckTestFixture.createSequentialDeck(hand);
        Player player = new Player(ParticipantName.nameOf("루키"));
        int cardCount = hand.size();
        for (int i = 0; i < cardCount; i++) {
            player.addCard(deck.drawCard());
        }

        // when
        WinStatus actual = player.determineResult(otherScore);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
