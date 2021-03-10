package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    private Deck deck;
    private List<Card> cards;

    @BeforeEach
    void setUp() {
        cards = Arrays.asList(Card.of("하트", "10"), Card.of("스페이드", "9"));
        deck = new Deck(cards);
    }

    @DisplayName("Deck에서 카드를 한장 뽑는다.")
    @Test
    void draw_card_test() {
        assertThat(deck.drawCard()).isEqualTo(Card.of("스페이드", "9"));
        assertThat(deck.drawCard()).isEqualTo(Card.of("하트", "10"));
    }
}