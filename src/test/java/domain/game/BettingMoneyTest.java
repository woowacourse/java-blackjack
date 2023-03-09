package domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BettingMoneyTest {

    private BettingMoney bettingMoney;

    @BeforeEach
    void init() {
        bettingMoney = BettingMoney.create("10000");
    }

    @ParameterizedTest(name = "create()는 호출하면 BettingMoney를 생성한다.")
    @ValueSource(strings = {"1", "100000000"})
    void create_whenCall_thenSuccess() {
        BettingMoney bettingMoney = assertDoesNotThrow(() -> BettingMoney.create("10000"));
        assertThat(bettingMoney)
                .isInstanceOf(BettingMoney.class);
    }

    @ParameterizedTest(name = "create()는 정수 값이 아닌 금액이 주어지면, 예외를 반환한다.")
    @ValueSource(strings = {"string", "!#@($", "", "12wow34"})
    void create_givenInvalidTypeMoney_thenFail(final String money) {
        assertThatThrownBy(() -> BettingMoney.create(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 정수 값이어야 합니다.");
    }
}
