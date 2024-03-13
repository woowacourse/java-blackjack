package model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DequeTest {

    @DisplayName("52장의 카드 생성하여 초기화한다")
    @Test
    public void testInitDeque() {
        Deque deque = new Deque();
        assertThat(deque.getCards()).hasSize(52);
    }
}
