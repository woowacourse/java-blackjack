package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardNumberTest {
    @Test
    @DisplayName("해당 카드가 ACE 넘버인지 확인")
    void isAce() {
        assertThat(CardNumber.ACE.isAce()).isTrue();
        assertThat(CardNumber.TEN.isAce()).isFalse();
    }
}
