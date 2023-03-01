import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DeckTest {
    @DisplayName("카드 생성 후 52장 반환 확인")
    @Test
    void 카드에서_생성_후_52장_반환_확인() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱이 비었습니다.");
    }
}
