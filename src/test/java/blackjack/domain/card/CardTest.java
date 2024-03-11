package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("equals & hashCode 재정의 테스트")
    void equalsTest() {
        Card card = new Card(CardShape.HEART, CardNumber.KING);

        assertThat(card).isEqualTo(new Card(CardShape.HEART, CardNumber.KING));
        assertThat(Objects.hash(card)).isEqualTo(Objects.hash(new Card(CardShape.HEART, CardNumber.KING)));
    }
}
