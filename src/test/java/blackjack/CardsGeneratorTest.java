package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.manager.CardsGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsGeneratorTest {

    @DisplayName("덱을 만들 때 카드의 개수는 52개이다")
    @Test
    void test1() {
        // given
        CardsGenerator cardsGenerator = new CardsGenerator();

        // when
        List<Card> cards = cardsGenerator.generate();

        // then
        assertThat(cards).hasSize(52);
    }
}
