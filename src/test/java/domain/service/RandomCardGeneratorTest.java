package domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.model.Card;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomCardGeneratorTest {

    private static final CardGenerator randomCardGenerator = RandomCardGenerator.getInstance();

    @Test
    @DisplayName("카드를 겸치지 않게 배분하기 테스트")
    public void testRandomGenerate() {
        //given
        randomCardGenerator.reset();
        Set<Card> cards = new HashSet<>();

        //when
        for (int i = 0; i < 52; i++) {
            cards.add(randomCardGenerator.generate());
        }

        //then
        assertThat(cards.size()).isEqualTo(52);
    }
}
