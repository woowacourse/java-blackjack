package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("BetMoney는 ")
public class BetMoneyTest {

    @ParameterizedTest(name = "배팅시 1,000원 단위로 입력가능하다 money = {0}")
    @ValueSource(ints = {-1, 980403, 12345})
    void 배팅시_1000_원_단위로_입력가능_하다(int money) {
        // given
        // when

        // then
        assertThatThrownBy(() -> new BetMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 1,000원 단위만 가능합니다");
    }
}
