import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardsTest {
    @Test
    void createTest() {
        Cards cards = new Cards();
        Card card = new Card("A", "스페이드");
        assertThat(cards.contains(card)).isTrue();
    }
}
