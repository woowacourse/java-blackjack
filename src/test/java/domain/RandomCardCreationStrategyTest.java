package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomCardCreationStrategyTest {

    @Test
    @DisplayName("52개의 카드를 잘 만듦")
    void creation_success() {
        //given
        int expectCardSize = 52;
        CardCreationStrategy strategy = new RandomCardCreationStrategy();

        //when
        List<Card> cards = strategy.create();

        //then
        Assertions.assertThat(cards.size()).isEqualTo(expectCardSize);
    }

}