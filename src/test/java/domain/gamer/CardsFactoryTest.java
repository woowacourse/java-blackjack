package domain.gamer;

import domain.card.CardsFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsFactoryTest {
    @Test
    @DisplayName("52장의 카드가 제대로 만들어 졌는지 확인하는 테스트")
    void createCorrectCardFactory() {
        assertThat(CardsFactory.getCards()).hasSize(52);
    }
}
