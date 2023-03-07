package domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AccountTest {

    @Test
    @DisplayName("요구사항에 맞게 배팅 금액의 조건이 충족하면 정상적으로 객체를 생성한다.")
    void create_success() {
        // when & then
        assertThatNoException()
                .isThrownBy(() -> new Account(1000));
     }

    @ParameterizedTest
    @ValueSource(ints = {-100, 0, 100, 900})
    @DisplayName("배팅 금액이 1000원 미만이면 예외가 발생한다.")
    void throws_exception_when_betting_account_under_boundary(int givenAccount) {
        // when & then
        assertThatThrownBy(() -> new Account(givenAccount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Message.BETTING_MONEY_NEED_MORE.getMessage());
     }
}
