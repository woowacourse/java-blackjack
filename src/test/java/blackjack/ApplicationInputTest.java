package blackjack;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import camp.nextstep.edu.missionutils.test.NsTest;

public class ApplicationInputTest extends NsTest {

    @Test
    @DisplayName("배팅 금액에 양수 이외의 값이 들어오면 예외 발생")
    void validateBettingMoney() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi, jason", "0"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[] {});
    }
}
