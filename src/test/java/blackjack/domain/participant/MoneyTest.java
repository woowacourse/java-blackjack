package blackjack.domain.participant;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.participant.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @DisplayName("1,000원 이상의 양수가 아닌 배팅금액을 생성하면 에러가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 999})
    public void createFail(int value) {
        assertThatCode(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("배팅금액은 1,000원 이상의 양수만 가능합니다. 입력값: %d", value));
    }

    @DisplayName("배팅 금액이 1,000원 이상이면 에러가 발생하지 않는다")
    @Test
    public void createSuccess() {
        assertThatCode(() -> new Money(1000))
                .doesNotThrowAnyException();
    }

    @DisplayName("배팅 금액은 더할 수 있다")
    @Test
    public void add() {
        Money money = new Money(1000);
        Money otherMoney = new Money(2000);

        Money result = money.plus(otherMoney);

        assertThat(result).isEqualTo(new Money(3000));
    }
}
