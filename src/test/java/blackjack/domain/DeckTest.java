package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = Deck.init();
    }

    @Test
    @DisplayName("52장의 카드가 존재한다.")
    void checkDeckCardsSize() {
        assertThat(deck.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("Deck에서 카드를 한 장 뽑는다.")
    void drawCard() {
        assertAll(
                () -> assertThat(deck.draw()).isInstanceOf(Card.class),
                () -> assertThat(deck.size()).isEqualTo(51)
        );
    }
}
