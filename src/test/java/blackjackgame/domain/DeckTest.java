package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @DisplayName("현재 인덱스가 가르키는 카드를 뽑는지 확인한다.")
    @Test
    void Should_ReturnCursorIndexCard_When_PickOneCard() {
        Card spade5Card = new Card(Symbol.SPADE, CardValue.FIVE);
        Card clover8Card = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Deck deck = new Deck(List.of(spade5Card, clover8Card));

        assertThat(deck.pickOne()).isEqualTo(spade5Card);
        assertThat(deck.pickOne()).isEqualTo(clover8Card);
    }

    @DisplayName("덱의 모든 카드를 소진하면 다시 셔플하여 재활용하는지 확인한다.")
    @Test
    void Should_ShuffleDeck_When_AllCardInDeckUsed() {
        Deck deck = new Deck();
        List<Card> firstDeck = IntStream.range(0,52)
                .mapToObj(i -> deck.pickOne())
                .collect(Collectors.toUnmodifiableList());

        List<Card> secondDeck = IntStream.range(0,52)
                .mapToObj(i -> deck.pickOne())
                .collect(Collectors.toUnmodifiableList());

        Assertions.assertThat(firstDeck).containsExactlyInAnyOrderElementsOf(secondDeck);
    }
}
