package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardValue;
import domain.card.Suit;
import domain.card.TrumpCard;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantHandTest {

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

    static Stream<Arguments> createNonBustCards() {
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

    static Stream<Arguments> createCalculateSumCards() {
        return Stream.of(
                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                                new TrumpCard(Suit.CLOVER, CardValue.NINE)
                        ),
                        Score.from(17)
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.K),
                                new TrumpCard(Suit.CLOVER, CardValue.SEVEN)
                        ),
                        Score.from(17)
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.K),
                                new TrumpCard(Suit.CLOVER, CardValue.SEVEN),
                                new TrumpCard(Suit.CLOVER, CardValue.FOUR)
                        ),
                        Score.from(21)
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.FIVE),
                                new TrumpCard(Suit.CLOVER, CardValue.K),
                                new TrumpCard(Suit.HEART, CardValue.A)
                        ),
                        Score.from(16)
                )
        );
    }

    static Stream<Arguments> createCalculateAceSumCards() {
        return Stream.of(
                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.A)
                        ),
                        Score.from(11),
                        Score.from(2)
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.K),
                                new TrumpCard(Suit.CLOVER, CardValue.A)
                        ),
                        Score.from(11),
                        Score.from(11)
                ),

                Arguments.of(List.of(new TrumpCard(Suit.DIAMOND, CardValue.K),
                                new TrumpCard(Suit.CLOVER, CardValue.A),
                                new TrumpCard(Suit.CLOVER, CardValue.A)
                        ),
                        Score.from(21),
                        Score.from(12)
                )
        );
    }

    @Test
    void 카드를_추가한다() {
        // given
        TrumpCard card = new TrumpCard(Suit.CLOVER, CardValue.A);
        ParticipantHand hand = new ParticipantHand();

        // when
        hand.addCard(card);
        TrumpCard addCard = hand.getCards().getFirst();

        // then
        TrumpCard expected = new TrumpCard(Suit.CLOVER, CardValue.A);
        assertThat(addCard).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("createBustCards")
    void 카드의_합이_21_초과면_버스트다(List<TrumpCard> trumpCards) {
        // given
        ParticipantHand hand = new ParticipantHand();

        // when
        for (int i = 0; i < trumpCards.size(); i++) {
            hand.addCard(trumpCards.get(i));
        }

        // then
        Score totalScore = hand.calculateCardSum();
        assertThat(hand.isBust(totalScore)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("createNonBustCards")
    void 카드의_합이_21_이하면_뽑을_수_있다(List<TrumpCard> trumpCards) {
        // given
        ParticipantHand hand = new ParticipantHand();

        // when
        for (int i = 0; i < trumpCards.size(); i++) {
            hand.addCard(trumpCards.get(i));
        }

        // then
        Score totalScore = hand.calculateCardSum();
        assertThat(hand.isBust(totalScore)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("createCalculateSumCards")
    void 카드의_합을_계산한다(List<TrumpCard> cards, Score expected) {
        // given
        ParticipantHand hand = new ParticipantHand();
        for (int i = 0; i < cards.size(); i++) {
            hand.addCard(cards.get(i));
        }

        // when
        Score totalScore = hand.calculateCardSum();

        // then
        assertThat(totalScore).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("createCalculateAceSumCards")
    void 에이스를_최적의해로_카드의_합을_구한다(List<TrumpCard> cards, Score aceStandard, Score expected) {
        // given
        ParticipantHand hand = new ParticipantHand();
        for (int i = 0; i < cards.size(); i++) {
            hand.addCard(cards.get(i));
        }

        // when
        Score totalScore = hand.calculateCardSum(aceStandard);

        // then
        assertThat(totalScore).isEqualTo(expected);
    }
}
