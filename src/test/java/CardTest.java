import domain.Card;
import domain.Number;
import domain.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("카드는 모양과 숫자를 가진다.")
    @Test
    void createCardSuccess() {
        Card card = new Card(Shape.HEART, Number.A);
        Assertions.assertThat(card).extracting("shape")
                .isEqualTo(Shape.HEART);
    }
}

