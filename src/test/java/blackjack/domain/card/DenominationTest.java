package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
        assertThatThrownBy(() -> {
            Denomination.of("Ace");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 명칭입니다. 값: Ace");

        assertThatThrownBy(() -> {
            Denomination.of("1");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 명칭입니다. 값: 1");
    }
}
