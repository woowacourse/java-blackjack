package model.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import model.card.exception.InvalidDeckSizeException;
import model.card.exception.NoMoreCardException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RandomDeckTest {
    private static final int REQUIRED_DECK_SIZE = 52;

    @Test
    void 덱이_처음에_가지는_카드가_52개가_아니면_예외를_발생한다() {
        // given
        List<Card> cards = List.of(
                Card.of(Suit.CLOVER, Rank.JACK),
                Card.of(Suit.CLOVER, Rank.QUEEN),
                Card.of(Suit.CLOVER, Rank.KING)
        );

        // when and then
        assertThatThrownBy(() -> RandomDeck.from(cards))
                .isInstanceOf(InvalidDeckSizeException.class);
    }

    @ParameterizedTest
    @MethodSource("provideValidDeck")
    void 덱의_사이즈를_초과해서_드로우하면_예외를_발생한다(Deck deck) {
        // given
        for (int i = 0; i < REQUIRED_DECK_SIZE; i++) {
            deck.draw();
        }

        // when and then
        assertThatThrownBy(deck::draw)
                .isInstanceOf(NoMoreCardException.class);
    }

    private static Stream<Arguments> provideValidDeck() {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(Card.of(suit, rank));
            }
        }

        return Stream.of(
                Arguments.of(RandomDeck.from(cards))
        );
    }
}
