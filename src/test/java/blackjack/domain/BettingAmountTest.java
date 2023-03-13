package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAmountTest {
    @DisplayName("BattingAmount를 생성한다.")
    @Test
    void Should_Create_When_NewName() {
        BettingAmount bettingAmount = new BettingAmount(10000);

        assertThat(bettingAmount.getBettingAmount()).isEqualTo(10000);
    }
}
