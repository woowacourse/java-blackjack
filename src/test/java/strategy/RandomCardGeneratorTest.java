package strategy;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomCardGeneratorTest {

    @Test
    @DisplayName("성공: 52장의 카드를 생성한다.")
    void generate_noException_generateFiftyTwoCards() {
        CardGenerator generator = new RandomCardGenerator();
        Queue<Card> generate = generator.generate();
        assertThat(generate).hasSize(52);
    }
}
