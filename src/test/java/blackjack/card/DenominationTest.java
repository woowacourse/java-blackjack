package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DenominationTest {

    @Test
    @DisplayName("Ace 카드가 아니면 False를 반환한다.")
    void isAce_false() {
        assertThat(Denomination.TWO.isAce()).isFalse();
    }

    @Test
    @DisplayName("Ace 카드이면 True를 반환한다.")
    void isAce_true() {
        assertThat(Denomination.ACE.isAce()).isTrue();
    }
}
