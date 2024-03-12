package domain.game.deck;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomDeckGeneratorTest {
    @DisplayName("블랙잭에 필요한 카드들을 생성하고 섞는다.")
    @Test
    void createCards() {
        RandomDeckGenerator generator = new RandomDeckGenerator();
        Deck deck = generator.generate();
        Assertions.assertThat(deck.getTotalSize()).isEqualTo(52);
    }
}
