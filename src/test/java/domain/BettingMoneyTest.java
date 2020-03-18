package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {
    @DisplayName("money 객체를 잘 생성하는지 테스트")
    @Test
    void constructorTest() {
        Assertions.assertThat(new BettingMoney(10000)).isInstanceOf(BettingMoney.class);
    }

    @DisplayName("money 객체 오류 테스트")
    @Test
    void throw_constructorTest() {
        Assertions.assertThatThrownBy(()->new BettingMoney(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}
