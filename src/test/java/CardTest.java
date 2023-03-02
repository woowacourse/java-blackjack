import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Card;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @DisplayName("카드의 값과 모양으로 카드를 생성한다.")
    @Test
    void createTest() {
        assertDoesNotThrow(() -> new Card("A", "스페이드"));
        assertThatThrownBy(() -> new Card("깃짱", "하트"))
                .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> new Card("11", "망고"))
                .isInstanceOf(NoSuchElementException.class);
    }
}
