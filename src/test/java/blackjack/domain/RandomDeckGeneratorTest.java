package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomDeckGeneratorTest {

    @Test
    @DisplayName("generator가 중복없는 52장의 카드뭉치를 반환하는지 테스트`")
    void generateDistinctDeck() {
        final DeckGenerator generator= new RandomDeckGenerator();

        final long uniqueCardCount = generator.generate().stream()
                .distinct().count();

        assertThat(uniqueCardCount).isEqualTo(52);
    }
}
