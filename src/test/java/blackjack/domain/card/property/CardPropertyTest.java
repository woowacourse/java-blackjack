package blackjack.domain.card.property;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardPropertyTest {
    @Test
    @DisplayName("같은 모양과 같은 숫자의 카드특성은 한가지만 존재해야 합니다.")
    void of() {
        CardProperty cardProperty = CardProperty.of(CardShape.HEART, CardNumber.A);

        assertThat(cardProperty).isSameAs(CardProperty.of(CardShape.HEART, CardNumber.A));
    }
}