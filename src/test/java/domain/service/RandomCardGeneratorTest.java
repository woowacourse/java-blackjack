package domain.service;

import domain.model.Card;
import java.util.HashSet;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomCardGeneratorTest {

    private final CardGenerator randomCardGenerator = new RandomCardGenerator();

    @Test
    @DisplayName("카드를 겸치지 않게 배분하기 테스트")
    public void testRandomGenerate() {
        //given
        Set<Card> cards = new HashSet<>();

        //when
        for (int i = 0; i < 52; i++) {
            cards.add(randomCardGenerator.generate());
        }

        //then
        Assertions.assertThat(cards.size()).isEqualTo(52);
    }
}
