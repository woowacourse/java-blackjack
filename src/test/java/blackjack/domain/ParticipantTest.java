package blackjack.domain;

import blackjack.domain.strategy.ManualCardStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

        final List<Card> actualCards = participant.getCards();
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    private static Stream<Arguments> provideForDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.ACE),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardPattern.SPADE, CardNumber.ACE),
                                new Card(CardPattern.HEART, CardNumber.EIGHT),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT)
                        )
                )
        );
    }

}
