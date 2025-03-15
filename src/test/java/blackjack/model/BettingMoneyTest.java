package blackjack.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @Nested
    @DisplayName("베팅 머니 생성")
    class BattingMoneyCreate {

        @ValueSource(ints = {999, 1_000_001})
        @ParameterizedTest
        void 베팅_금액_범위를_벗어나면_베팅_머니를_생성할_수_없다(int amount) {
            assertThatThrownBy(() -> new BettingMoney(amount))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ValueSource(ints = {1_000, 1_000_000})
        @ParameterizedTest
        void 베팅_금액_범위에_들어가면_베팅_머니를_생성할_수_있다(int amount) {
            assertDoesNotThrow(() -> new BettingMoney(amount));
        }

    }

}
