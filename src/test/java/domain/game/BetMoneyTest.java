package domain.game;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BetMoneyTest {

    @Test
    void 배팅금액이_음수인_경우_예외발생() {
        assertThatThrownBy(() -> new BetMoney(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 배팅금액이_0인경우_예외발생() {
        assertThatThrownBy(() -> new BetMoney(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 배팅금액이_1억이_넘을_경우_예외발생() {
        assertThatThrownBy(() -> new BetMoney(100_000_001))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 100_000_000})
    void 배팅금액이_1억이하의_자연수인경우_정상적으로_생성(int amount) {
        assertThatCode(() -> new BetMoney(amount))
                .doesNotThrowAnyException();
    }
}