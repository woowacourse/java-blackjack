package team.blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    @Test
    void 전체_52장_드로우_시_중복되지_않는다() {
        Deck deck = new Deck();

        List<Card> drawnCards = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            Card card = deck.draw();
            drawnCards.add(card);
        }

        assertThat(new HashSet<>(drawnCards)).hasSize(52);
    }
}
