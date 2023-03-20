package domain.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드의 타입과 값을 주입하면 카드 객체가 정상적으로 생성됨")
    void generateCard() {
        CardType type = CardType.CLOVER;
        CardValue value = CardValue.ACE;

        Assertions.assertDoesNotThrow(() -> new Card(type, value));
    }
}

