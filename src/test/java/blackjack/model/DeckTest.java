package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeckTest {

    @Test
    @DisplayName("카드 덱은 총 52장으로 이루어져야한다.")
    void deckSizeTest() {
        // given
        Deck deck = new Deck();
        // when & then
        assertThat(deck.getSize()).isEqualTo(52);
    }

    @Test
    @DisplayName("현재 카드 덱에는 중복이 없다.")
    void notDuplicateCardInDeckTest() {
        // given
        Deck deck = new Deck();
        // when
        List<Card> cards = deck.getCards();
        cards.add(new Card(Figure.CLOVER, Number.ACE));

        HashSet<Card> newDeck = new HashSet<>(cards);
        // then
        assertThat(newDeck.size()).isEqualTo(52);
    }
}