package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DenominationTest {

    @Test
    void 카드_숫자는_총_13가지이다() {
        final Denomination[] denominations = Denomination.values();
        assertThat(denominations.length).isEqualTo(13);
    }
}
