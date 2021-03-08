package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardFactoryTest {

    @DisplayName("카드 52장 생성 테스트")
    @Test
    void createTest() {
        assertThat(CardFactory.create().size()).isEqualTo(52);
    }
}