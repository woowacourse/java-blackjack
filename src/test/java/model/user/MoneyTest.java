package model.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("게임에서 질 경우 돈의 값이 마이너스로 바뀐다.")
    void whenLoseGame_thenReturnLoseMoney() {
        // given
        final Money money = new Money(10_000L);

        // when
        final Money loseMoney = money.lose();

        // then
        Assertions.assertThat(loseMoney).isEqualTo(new Money(-10_000L));
    }
}
