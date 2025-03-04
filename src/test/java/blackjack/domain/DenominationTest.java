package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Nested
class DenominationTest {

    @Nested
    @DisplayName("에이스 값 테스트")
    class AceTest {

        @Test
        @DisplayName("합에 따라서 에이스를 1로 계산한다.")
        void notChangeAceValue() {
            int sum = 17;
            assertThat(Denomination.changeAceValue(sum)).isEqualTo(17);
        }

        @Test
        @DisplayName("합에 따라서 에이스를 11로 계산한다.")
        void changeAceValue() {
            int sum = 11;
            assertThat(Denomination.changeAceValue(sum)).isEqualTo(21);
        }
    }
}