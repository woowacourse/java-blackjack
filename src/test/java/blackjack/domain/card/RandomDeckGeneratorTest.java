package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomDeckGeneratorTest {

    @Test
    @DisplayName("generator가 중복없는 52장의 카드뭉치를 반환하는지 테스트`")
    void generateDistinctDeck() {
        final DeckGenerator generator = new RandomDeckGenerator();

        final long uniqueCardCount = generator.generate().stream()
                .distinct().count();

        assertThat(uniqueCardCount).isEqualTo(52);
    }
}
