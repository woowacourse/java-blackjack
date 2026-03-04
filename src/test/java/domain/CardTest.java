package domain;

import org.junit.jupiter.api.Test;
import util.NumberGenerator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardTest {

    class DefaultShapeNumberGenerator implements NumberGenerator {
        @Override
        public int generate() {
            return 1;
        }
    }

    @Test
    void 랜덤_카드_모양_테스트() {
        // given
        NumberGenerator numberGenerator = new DefaultShapeNumberGenerator();

        // when
        CardShape cardShape = CardShape.getShape(numberGenerator.generate());

        // then
        assertThat(cardShape).isEqualTo(CardShape.HEART);
    }
}
