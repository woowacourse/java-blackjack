package blackjack.domain;

import blackjack.domain.strategy.ManualCardStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

    private final ManualCardStrategy manualCardStrategy = new ManualCardStrategy();

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
        return Stream.of(
                Arguments.of(
                        List.of(new Card(pattern, 1),
                                new Card(pattern, 1))
                ),
                Arguments.of(
                        List.of(new Card(pattern, 1),
                                new Card(pattern, 2),
                                new Card(pattern, 1))
                )
        );
    }

}
