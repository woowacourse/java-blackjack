package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드의 타입과 숫자를 입력했을 때 카드 객체가 정상적으로 생성된다.")
    void makeCard() {
        Type type = Type.CLOVER;
        Number number = Number.A;

        Assertions.assertDoesNotThrow(() -> new Card(type, number));
    }
}

