import static domain.card.Number.ACE;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("A 판별 테스트")
    void isATest() {
        Card card = new Card(SPADE, ACE);
        assertThat(card.isA()).isTrue();
    }
}
