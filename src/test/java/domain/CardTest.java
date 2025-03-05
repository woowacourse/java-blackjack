package domain;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void 카드가_정상적으로_생성된다() {
        assertThatNoException().isThrownBy(() -> new Card(CardShape.HEART, CardNumber.ACE));
    }
}
