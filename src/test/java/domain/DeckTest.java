package domain;

import domain.constant.Rank;
import domain.constant.Suit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    void 덱은_52장의_카드로_구성된다() {
        Deck deck = new Deck();

        int result = deck.size();

        assertThat(result).isEqualTo(52);
    }

    @Test
    void 덱의_초기_카드는_무늬순_숫자순으로_정렬되어있다() {
        Deck deck = new Deck();

        List<Card> result = new ArrayList<>();
        int deckSize = deck.size();
        for (int i = 0; i < deckSize; i++) {
            result.add(deck.draw());
        }

        List<Card> expectedDeck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                expectedDeck.add(new Card(rank, suit));
            }
        }

        assertThat(result).containsExactlyElementsOf(expectedDeck);
    }

    @Test
    void 카드_셔플_테스트() {
        Deck deck1 = new Deck();
        deck1.shuffle();
        int deckSize = deck1.size();

        List<Card> result1 = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            Card card = deck1.draw();
            result1.add(card);
        }

        Deck deck2 = new Deck();
        List<Card> result2 = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            Card card = deck2.draw();
            result2.add(card);
        }

        assertThat(result1.size()).isEqualTo(result2.size());
        assertThat(result1).containsExactlyInAnyOrderElementsOf(result2);
    }
}
