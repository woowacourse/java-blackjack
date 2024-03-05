package domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("카드를 생성한다.")
    @Test
    void createCardTest() {
        // given
        Symbol symbol = Symbol.HEART;
        Number number = Number.TWO;

        // then
        assertThatCode(() -> new Card(symbol, number))
                .doesNotThrowAnyException();
    }
}
