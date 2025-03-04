import domain.CardShape;
import domain.NumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardShapeTest {
    @DisplayName("네 개의 문양 중 하나의 문양을 랜덤으로 뽑는다.")
    @Test
    void test() {
        NumberGenerator numberGenerator = new TestNumberGenerator();
        CardShape cardShape = CardShape.randomPick(numberGenerator);

        Assertions.assertThat(cardShape).isEqualTo(CardShape.DIA);
    }
}
