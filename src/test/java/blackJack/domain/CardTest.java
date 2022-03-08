package blackJack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 생성 테스트")
    void createValidCard() {
        assertThat(new Card(Symbol.SPADE, Denomination.K)).isNotNull();
    }
}
