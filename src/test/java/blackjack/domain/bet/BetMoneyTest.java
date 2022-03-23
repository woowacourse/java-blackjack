package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetMoneyTest {

    private static final String BETTING_MONEY_ERROR_MESSAGE = "베팅 금액으로 1 이상 1,000,000 이하의 숫자를 입력해주세요.";

    @Test
    @DisplayName("BetMoney 클래스는 1이상 1,000,000 이하의 수를 입력받으면 정상적으로 생성된다.")
    void create_BetMoney() {
        assertThatCode(() -> new BetMoney("10000")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("BetMoney 클래스는 수가 아닌 문자를 입력받으면 에러를 출력한다.")
    void validate_not_integer() {
        assertThatThrownBy(() -> new BetMoney("만원"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BETTING_MONEY_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("BetMoney 클래스는 0 이하의 수를 입력받으면 에러를 출력한다.")
    void validate_not_positive() {
        assertThatThrownBy(() -> new BetMoney("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BETTING_MONEY_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("BetMoney 클래스는 1,000,000 을 넘는 수를 입력받으면 에러를 출력한다.")
    void validate_too_big_integer() {
        assertThatThrownBy(() -> new BetMoney("1000001"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BETTING_MONEY_ERROR_MESSAGE);
    }
}
