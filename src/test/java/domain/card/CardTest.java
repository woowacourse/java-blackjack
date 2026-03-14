package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @DisplayName("동일한 숫자와 문양의 카드를 요청하면 같은 인스턴스를 반환한다")
    @Test
    void staticFactoryMethod_SameInstance_ReturnTrue() {
        Card card1 = Card.from("A", "스페이드");
        Card card2 = Card.from("A", "스페이드");

        assertThat(card1).isSameAs(card2);
    }
}
