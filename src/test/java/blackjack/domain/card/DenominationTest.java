package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DenominationTest {

    @Test
    void 생성_및_비교() {
        // given, when
        Denomination denomination1 = Denomination.of("2");
        Denomination denomination2 = Denomination.of("2");

        // then
        assertThat(denomination1).isSameAs(denomination2);
    }

    @DisplayName("명칭은 2부터 10 그리고 K, Q, J, A만 존재한다.")
    @Test
    void 유효성_검사() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Denomination.of("Ace");
        });
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Denomination.of("1");
        });
    }

    @DisplayName("에이스 비교 테스트")
    @Test
    void isAce() {
        // given, when
        Denomination denomination1 = Denomination.of("A");

        // then
        assertThat(denomination1).isSameAs(Denomination.ACE);
    }
}
