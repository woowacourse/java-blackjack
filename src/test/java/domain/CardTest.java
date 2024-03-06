package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드가 잘 생성된다.")
    void constructorSuccessTest() {
        Assertions.assertThatNoException()
                .isThrownBy(() -> new Card(Shape.HEART, Rank.ACE));
    }
}
