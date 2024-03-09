package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    @DisplayName("덱에서 카드 하나를 뽑는다.")
    void draw_ByIndex() {
        DrawStrategy drawStrategy = (size) -> 0;
        Deck deck = new Deck(drawStrategy);

        assertThat(deck.draw().toString()).isEqualTo("A스페이드");
    }

    @Test
    @DisplayName("처음 두장을 뽑는다.")
    void drawInitialHands() {
        RandomDrawStrategy randomDrawStrategy = new RandomDrawStrategy();
        Deck deck = new Deck(randomDrawStrategy);

        assertThat(deck.drawInitialHands()).hasSize(2);
    }
}
