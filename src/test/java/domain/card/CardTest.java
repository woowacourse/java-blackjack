package domain.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @DisplayName("카드의 값과 모양으로 카드를 생성한다.")
    @Test
    void createTest() {
        assertDoesNotThrow(() -> new Card("스페이드", "A"));
        assertThatThrownBy(() -> new Card("하트", "깃짱"))
                .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> new Card("망고", "11"))
                .isInstanceOf(NoSuchElementException.class);
    }
}
