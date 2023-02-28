package card;

import static card.CardNumber.ACE;
import static card.Pattern.HEART;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    @DisplayName("카드를 생성할 수 있다.")
    void create() {
        assertThatCode(() -> new Card(ACE, HEART))
                .doesNotThrowAnyException();
    }
}
