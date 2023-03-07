package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DenominationTest {

    @DisplayName("카드의 포인트를 확인한다.")
    @Test
    void getPoint() {
        Assertions.assertThat(Denomination.ACE.getPoint()).isEqualTo(1);
    }

    @DisplayName("카드의 이름을 확인한다.")
    @Test
    void getName() {
        Assertions.assertThat(Denomination.ACE.getName()).isEqualTo("A");
    }
}
