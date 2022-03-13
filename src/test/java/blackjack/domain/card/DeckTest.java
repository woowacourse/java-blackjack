package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class DeckTest {

    private static final int TOTAL_SIZE = 52;

    @Test
    @DisplayName("덱에서 카드를 한 장 반환한다.")
    void drawCard() {
        Deck deck = new Deck();
        Card card = deck.draw();

        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("덱에서 draw된 카드는 서로 다르다.")
    void drawDifferentCard() {
        Deck deck = new Deck();
        Card card1 = deck.draw();
        Card card2 = deck.draw();

        assertThat(card1).isNotEqualTo(card2);
    }

    @Test
    @DisplayName("덱에서 draw된 카드는 덱에서 제외되어야 한다.")
    void pickedCardNotInDeck() {
        Deck deck = new Deck();
        Card card = deck.draw();
        List<Card> cards = new ArrayList<>();
        IntStream.range(0, TOTAL_SIZE - 1)
                        .forEach(i -> cards.add(deck.draw()));

        assertThat(cards.contains(card)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("getCombinationOfCard")
    @DisplayName("덱에 카드가 포함되어 있는지 확인한다.")
    void checkContainsCard(Card card) {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        IntStream.range(0, TOTAL_SIZE)
                .forEach(i -> cards.add(deck.draw()));

        assertThat(cards.contains(card)).isTrue();
    }

    private static Stream<Card> getCombinationOfCard() {
        return Stream.of(
                new Card(Type.SPADE, Score.ACE),
                new Card(Type.SPADE, Score.THREE),
                new Card(Type.CLOVER, Score.FIVE),
                new Card(Type.CLOVER, Score.SEVEN),
                new Card(Type.DIAMOND, Score.TEN),
                new Card(Type.DIAMOND, Score.JACK),
                new Card(Type.HEART, Score.KING),
                new Card(Type.HEART, Score.QUEEN)
        );
    }
}
