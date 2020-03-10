package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    @DisplayName("같은 카드 추가 테스트")
    void addCard() {
        Cards cards = new Cards();
        Card card = new Card(Type.DIAMOND, Symbol.ACE);
        cards.add(card);
        Assertions.assertThatThrownBy(() -> cards.add(card))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
