package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    @DisplayName("덱에서 카드 하나를 뽑는다.")
    void draw_ByIndex() {
        Deck deck = new Deck();
        DrawStrategy drawStrategy = (size) -> 0;

        assertThat(deck.draw(drawStrategy).toString()).isEqualTo("A스페이드");
    }
}
