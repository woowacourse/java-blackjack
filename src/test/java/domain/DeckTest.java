package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    void 카드를_52장_생성한다() {
        Deck deck = new Deck();

        int deckSize = deck.size();

        assertThat(deckSize).isEqualTo(52);
    }

    @Test
    void 카드는_숫자순에서_무늬순으로_생성된다() {
        Deck deck = new Deck();

        int deckSize = deck.size();

        List<Card> result = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            Card card = deck.draw();
            result.add(card);
        }

        assertThat(result.size()).isEqualTo(52);
    }

    @Test
    void 카드_셔플_테스트() {
        Deck deck1 = new Deck();

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
