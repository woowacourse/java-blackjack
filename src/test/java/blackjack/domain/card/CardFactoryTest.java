package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardFactoryTest {

    @DisplayName("카드 52장 생성 테스트")
    @Test
    void createTest() {
        assertThat(CardFactory.generate().size()).isEqualTo(52);
    }
}