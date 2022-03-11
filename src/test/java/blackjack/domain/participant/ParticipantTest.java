package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import blackjack.domain.card.strategy.ManualCardStrategy;

class ParticipantTest {

    private final ManualCardStrategy manualCardStrategy = new ManualCardStrategy();

    @ParameterizedTest
    @MethodSource("provideForDrawCardTest")
    @DisplayName("참여자는 카드를 뽑을 수 있어야 한다.")
    void drawCardTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Participant participant = new Participant();

        for (int i = 0; i < expectedCards.size(); i++) {
            participant.drawCard(deck);
        }

        final List<String> actualCardNames = participant.getCardNames();
        final List<String> expectedCardNames = expectedCards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toUnmodifiableList());
        assertThat(actualCardNames).isEqualTo(expectedCardNames);
    }

    private static Stream<Arguments> provideForDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.EIGHT, CardPattern.HEART),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForCannotContinueDrawCardTest")
    @DisplayName("카드의 합계가 21 초과인지 확인할 수 있어야 한다.")
    void cannotContinueDrawTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Participant participant = new Participant();

        for (int i = 0; i < expectedCards.size(); i++) {
            participant.drawCard(deck);
        }

        assertThat(participant.isBurst()).isTrue();
    }

    private static Stream<Arguments> provideForCannotContinueDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.QUEEN, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.DIAMOND)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.HEART),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForCanContinueDrawCardTest")
    @DisplayName("카드의 합계가 21 이하인지 확인할 수 있어야 한다.")
    void canContinueDrawTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Participant participant = new Participant();

        for (int i = 0; i < expectedCards.size(); i++) {
            participant.drawCard(deck);
        }

        assertThat(participant.isBurst()).isFalse();
    }

    private static Stream<Arguments> provideForCanContinueDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.THREE, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.TWO, CardPattern.SPADE),
                                new Card(CardNumber.SEVEN, CardPattern.SPADE),
                                new Card(CardNumber.THREE, CardPattern.SPADE)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForIsHigherThanTest")
    @DisplayName("두 참여자의 카드 합계를 비교한다.")
    void isHigherThanTest(final List<Card> initializedCards, final int drawCount1, final int drawCount2) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);

        final Participant participant1 = new Participant();
        final Participant participant2 = new Participant();
        drawCards(participant1, deck, drawCount1);
        drawCards(participant2, deck, drawCount2);

        assertThat(participant1.isHigherThan(participant2)).isTrue();
    }

    private void drawCards(final Participant participant, final Deck deck, final int drawCount) {
        for (int i = 0; i < drawCount; i++) {
            participant.drawCard(deck);
        }
    }

    private static Stream<Arguments> provideForIsHigherThanTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.SPADE),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.NINE, CardPattern.SPADE)
                        ), 2, 2
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.TEN, CardPattern.SPADE),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.NINE, CardPattern.SPADE),
                                new Card(CardNumber.EIGHT, CardPattern.HEART)
                        ), 2, 2
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.SPADE),
                                new Card(CardNumber.THREE, CardPattern.SPADE),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.NINE, CardPattern.HEART)
                        ), 2, 3
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.SPADE),
                                new Card(CardNumber.KING, CardPattern.SPADE),
                                new Card(CardNumber.THREE, CardPattern.SPADE),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.NINE, CardPattern.HEART)
                        ), 3, 3
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.ACE, CardPattern.DIAMOND),
                                new Card(CardNumber.ACE, CardPattern.HEART),
                                new Card(CardNumber.TWO, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.SPADE)
                        ), 3, 2
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.FOUR, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.SPADE),
                                new Card(CardNumber.ACE, CardPattern.DIAMOND),
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.ACE, CardPattern.HEART)
                        ), 2, 3
                )
        );
    }


}
