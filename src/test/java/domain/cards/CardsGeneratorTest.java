package domain.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CardsGeneratorTest {

    @DisplayName("서로 다른 52장의 카드팩을 생성한다.")
    @Test
    void generateCardsTest() {
        List<Card> cards = CardsGenerator.generateRandomCards();
        Assertions.assertThat(cards).hasSize(52);
    }
}
