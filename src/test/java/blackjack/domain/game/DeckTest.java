package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.game.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DeckTest {
    private static Stream<Arguments> makeEmptyDeck() {
        Deck shuffledDeck = Deck.createShuffledDeck();
        for (int i = 0; i < 52; i++) {
            shuffledDeck.draw();
        }
        return Stream.of(arguments(shuffledDeck));
    }

    @DisplayName("덱의 카드가 모두 반환되면, 더이상 반환을 요청할 수 없다")
    @ParameterizedTest
    @MethodSource("makeEmptyDeck")
    void should_ThrowIllegalArgumentException_When_NoMoreCard(Deck emptyDeck) {
        assertThatThrownBy(emptyDeck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱에 카드가 더 이상 없습니다");
    }

    @DisplayName("카드 덱은 카드를 한장씩 반환한다")
    @Test
    void should_giveOneCard() {
        Deck shuffledDeck = Deck.createShuffledDeck();
        assertThat(shuffledDeck.draw()).isInstanceOf(Card.class);
    }
}
