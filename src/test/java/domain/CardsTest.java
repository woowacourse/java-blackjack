package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("Cards 객체 생성 테스트")
    @Test
    void CardsTest() {
        Cards cards = new Cards();

        Assertions.assertThat(cards).isInstanceOf(Cards.class);
    }

    @DisplayName("객체의 복사 테스트")
    @Test
    void deepDuplicateTest() {
        Cards cards = new Cards();

        Assertions.assertThat(cards).extracting("cardsDeck").isEqualTo(Card.getCards());
    }
}
