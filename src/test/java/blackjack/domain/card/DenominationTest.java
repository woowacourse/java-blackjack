package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class DenominationTest {

    @Test
    @DisplayName("of를 통한 enum 객체 테스트")
    void testDenomination() {
        Denomination denomination = Denomination.of("2");
        assertThat(denomination).isEqualTo(Denomination.TWO);
    }
}