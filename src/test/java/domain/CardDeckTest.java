package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDeckTest {
    @DisplayName("개수 이상 드로우시 예외발생 테스트")
    @Test
    void cardDraw() {
        for (int i = 0; i < 52; i++) {
            CardDeck.draw();
        }
        assertThatThrownBy(() -> CardDeck.draw())
                .isInstanceOf(IndexOutOfBoundsException.class);
    }
}
