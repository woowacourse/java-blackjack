package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BattingAmountTest {
    @DisplayName("BattingAmount를 생성한다.")
    @Test
    void Should_Create_When_NewName() {
        BattingAmount battingAmount = new BattingAmount(10000);

        assertThat(battingAmount.getBattingAmount()).isEqualTo(10000);
    }
}
