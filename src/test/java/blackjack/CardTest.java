package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class CardTest {
    @Test
    @DisplayName("카드가 잘 생성되었는지 확인")
    void create() {
        assertThatCode(() -> new Card(CardNumber.TWO, CardType.CLOVER))
                .doesNotThrowAnyException();
    }
}
