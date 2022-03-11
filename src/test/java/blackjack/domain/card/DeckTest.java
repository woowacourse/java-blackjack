package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.strategy.ManualDeckGenerator;

class DeckTest {

    private final ManualDeckGenerator manualCardStrategy = new ManualDeckGenerator();

    @ParameterizedTest
    @MethodSource("provideForValidateCardDuplicatedTest")
    @DisplayName("중복된 카드가 존재할 시 예외 발생")
    void validateCardDuplicatedTest(final List<Card> cards) {
        manualCardStrategy.initCards(cards);
        assertThatThrownBy(() -> Deck.generate(manualCardStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 카드는 존재할 수 없습니다.");
    }

    private static Stream<Arguments> provideForValidateCardDuplicatedTest() {
        final CardPattern pattern = CardPattern.CLOVER;
        final CardNumber number = CardNumber.ACE;
        final CardNumber otherNumber = CardNumber.TWO;

        return Stream.of(
                Arguments.of(
                        List.of(new Card(number, pattern),
                                new Card(number, pattern))
                ),
                Arguments.of(
                        List.of(new Card(otherNumber, pattern),
                                new Card(otherNumber, pattern),
                                new Card(otherNumber, pattern))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForDrawCardTest")
    @DisplayName("카드 덱에서 한장의 카드를 뽑는다.")
    void drawCardTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);

        final Deck deck = Deck.generate(manualCardStrategy);
        final List<Card> actualCards = new LinkedList<>();
        for (int i = 0; i < expectedCards.size(); i++) {
            actualCards.add(deck.drawCard());
        }

        assertThat(actualCards).isEqualTo(expectedCards);
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

    @Test
    @DisplayName("뽑을 수 있는 카드가 없을 시 예외 발생")
    void validateDrawCardTest() {
        manualCardStrategy.initCards(List.of(new Card(CardNumber.ACE, CardPattern.DIAMOND)));
        final Deck deck = Deck.generate(manualCardStrategy);
        deck.drawCard();
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("더 이상 뽑을 수 있는 카드가 없습니다.");
    }

}
