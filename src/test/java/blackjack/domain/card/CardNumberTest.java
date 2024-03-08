package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("카드 숫자 enum 테스트")
class CardNumberTest {

    @DisplayName("Ace에 해당하는 지 확인할 수 있다")
    @Test
    void testIsCardNumberAce() {
        assertAll(
                () -> assertThat(CardNumber.ACE.isAce()).isTrue(),
                () -> assertThat(CardNumber.TWO.isAce()).isFalse()
        );
    }
}