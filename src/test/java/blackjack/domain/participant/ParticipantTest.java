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
import blackjack.domain.card.generator.ManualDeckGenerator;

class ParticipantTest {

    private final ManualDeckGenerator manualCardStrategy = new ManualDeckGenerator();

    private Participant makeParticipant(final Deck deck) {
        return new Participant("Participant", deck) {
            @Override
            public boolean isPossibleToDrawCard() {
                return true;
            }
        };
    }

    @DisplayName("참여자는 카드를 뽑을 수 있어야 한다.")
    @ParameterizedTest
    @MethodSource("provideForDrawCardTest")
    void drawCardTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Participant participant = makeParticipant(deck);

        for (int i = 0; i < expectedCards.size() - 2; i++) {
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

    @DisplayName("카드의 합계가 버스트인지 확인할 수 있어야 한다.")
    @ParameterizedTest
    @MethodSource("provideForCheckBustTest")
    void checkBustTest(final List<Card> initializedCards, final boolean isBust) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Participant participant = makeParticipant(deck);

        for (int i = 0; i < initializedCards.size() - 2; i++) {
            participant.drawCard(deck);
        }

        assertThat(participant.isBust()).isEqualTo(isBust);
    }

    private static Stream<Arguments> provideForCheckBustTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.QUEEN, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.DIAMOND)
                        ), true
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.HEART),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND)
                        ), true
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND),
                                new Card(CardNumber.QUEEN, CardPattern.DIAMOND)
                        ), false
                )
        );
    }
}
