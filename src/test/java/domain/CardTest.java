package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThat(new Card(Symbol.SPADE, Type.EIGHT)).isNotNull();
    }

    @Test
    void getPoint() {
        Card card = new Card(Symbol.SPADE, Type.FIVE);
        assertThat(card.getPoint()).isEqualTo(5);
    }

    @Test
    void getName() {
        Card card = new Card(Symbol.SPADE, Type.QUEEN);
        assertThat(card.getName()).isEqualTo("Q스페이드");
    }
}