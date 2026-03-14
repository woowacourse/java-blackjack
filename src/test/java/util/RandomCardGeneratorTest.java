package util;

import static org.assertj.core.api.Assertions.assertThat;

import domain.model.CardRank;
import domain.model.CardShape;
import org.junit.jupiter.api.Test;

public class RandomCardGeneratorTest {
    class DefaultNumberGenerator implements NumberGenerator {
        private final int number;

        public DefaultNumberGenerator(int number) {
            this.number = number;
        }

        @Override
        public int generate() {
            return number;
        }
    }

    @Test
    void 원하는_값_카드_생성_테스트() {
        // given
        NumberGenerator rankGenerator = new DefaultNumberGenerator(1);  // ACE
        NumberGenerator shapeGenerator = new DefaultNumberGenerator(2);  // SPADE
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator(rankGenerator, shapeGenerator);

        // when
        CardRank rank = randomCardGenerator.generateRank();
        CardShape shape = randomCardGenerator.generateShape();

        // then
        assertThat(rank).isEqualTo(CardRank.ACE);
        assertThat(shape).isEqualTo(CardShape.SPADE);
    }
}
