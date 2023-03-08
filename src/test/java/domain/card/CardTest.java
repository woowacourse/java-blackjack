package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    
    @Test
    @DisplayName("해당 카드가 에이스인지 확인")
    void isAce() {
        Card card = new Card(Shape.HEART, Number.ACE);
        assertThat(card.isAce()).isTrue();
    }
}