package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Denomination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DenominationTest {

    @Test
    @DisplayName("Number는 A, 2- 10, J, Q, K를 포함하여 총 13개를 가지고 있다.")
    void generateNumber() {
        int numberTotalCount = Denomination.values().length;
        assertThat(numberTotalCount).isEqualTo(13);
    }
}
