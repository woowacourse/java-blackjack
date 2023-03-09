package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ShuffledDeckTest {
    @DisplayName("6덱 생성 후 312장 반환을 확인한다.")
    @Test
    void 카드에서_생성_후_52장_반환_확인() {
        Deck shuffledDeck = ShuffledDeck.createByCount(6);
        for (int i = 0; i < 6 * 52; i++) {
            shuffledDeck.draw();
        }
        assertThatThrownBy(shuffledDeck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱이 비었습니다.");
    }
}
