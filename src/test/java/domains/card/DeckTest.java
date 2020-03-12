package domains.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    @DisplayName("카드를 정상적으로 가져올 경우 deck의 사이즈 감소")
    @Test
    void draw_Success_DecreaseDeckSize() {
        Deck deck = new Deck();
        int initialSize = 52;

        deck.draw();

        assertThat(deck.isSize(--initialSize)).isTrue();
    }
}
