package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    
    @Test
    @DisplayName("더 이상 뽑을 수 있는 카드가 없는 경우 확인")
    void noCardTest() {
        for (int i = 0; i < 52; i++) {
            Card.draw();
        }

        assertThatThrownBy(Card::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("더 이상 뽑을 수 있는 카드가 없습니다.");
    }
}
