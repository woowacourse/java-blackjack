package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DenominationTest {

    @Nested
    @DisplayName("에이스 값 테스트")
    class AceTest {

        @Test
        @DisplayName("합에 따라서 에이스를 1로 계산한다.")
        void notChangeAceValue() {
            int cardSumContainsAce = 17;
            assertThat(Denomination.convertAceValue(cardSumContainsAce, 21)).isEqualTo(17);
        }

        @Test
        @DisplayName("합에 따라서 에이스를 11로 계산한다.")
        void changeAceValue() {
            int cardSumContainsAce = 11;
            assertThat(Denomination.convertAceValue(cardSumContainsAce, 21)).isEqualTo(21);
        }
    }
}
