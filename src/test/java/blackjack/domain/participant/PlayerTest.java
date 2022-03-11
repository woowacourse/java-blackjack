package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import blackjack.domain.card.strategy.ManualCardStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    private final ManualCardStrategy manualCardStrategy = new ManualCardStrategy();

    @ParameterizedTest(name = "[{index}] 플레이어명 : \"{0}\"")
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어명에 공백이 존재하면 예외 발생")
    void blankPlayerNameExceptionTest(final String name) {

        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어명은 공백이 될 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("provideForDrawCardTest")
    @DisplayName("참여자는 카드를 뽑을 수 있어야 한다.")
    void drawCardTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Participant participant = new Player("sun");

        for (int i = 0; i < expectedCards.size(); i++) {
            participant.drawCard(deck);
        }

        final List<Card> actualCards = participant.openAllCards();
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

    @ParameterizedTest
    @MethodSource("provideForCannotContinueDrawCardTest")
    @DisplayName("카드의 합계가 21 초과인지 확인할 수 있어야 한다.")
    void cannotContinueDrawTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Participant participant = new Player("sun");

        for (int i = 0; i < expectedCards.size(); i++) {
            participant.drawCard(deck);
        }

        assertThat(participant.isBust()).isTrue();
    }

    private static Stream<Arguments> provideForCannotContinueDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT),
                                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                                new Card(CardPattern.DIAMOND, CardNumber.QUEEN),
                                new Card(CardPattern.DIAMOND, CardNumber.KING)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardPattern.SPADE, CardNumber.ACE),
                                new Card(CardPattern.HEART, CardNumber.TEN),
                                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT)
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
        final Participant participant = new Player("sun");

        for (int i = 0; i < expectedCards.size(); i++) {
            participant.drawCard(deck);
        }

        assertThat(participant.isBust()).isFalse();
    }

    private static Stream<Arguments> provideForCanContinueDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.KING),
                                new Card(CardPattern.DIAMOND, CardNumber.THREE),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardPattern.SPADE, CardNumber.ACE),
                                new Card(CardPattern.SPADE, CardNumber.TWO),
                                new Card(CardPattern.SPADE, CardNumber.SEVEN),
                                new Card(CardPattern.SPADE, CardNumber.THREE)
                        )
                )
        );
    }
}