package model.bet;

import bet.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {

 /*   @DisplayName("배팅 금액이 1000원 이상인 경우 Money 생성")
    @Test
    void createMoneyTest() {
        //given
        //when
        //then
        Assertions.assertThatCode(() -> new Money(1000)).doesNotThrowAnyException();
    }
    @DisplayName("배팅 금액이 1000원 이상이 아닌 경우 예외 발생")
    @Test
    void checkBetMoneyRangeTest() {
        //given
        //when
        //then
        Assertions.assertThatThrownBy(() -> new Money(900))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 1000원 이상부터 가능합니다.");
    }
    @DisplayName("배팅 금액이 100원 단위가 아닌 경우 예외 발생")
    @Test
    void checkBetMoneyUnitTest() {
        //given
        //when
        //then
        Assertions.assertThatThrownBy(() -> new Money(1105))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 100원 단위만 가능합니다.");
    }*/

    @DisplayName("Money Reduce Test")
    @Test
    void reduceTest() {
        List<Money> monies = List.of(
                new Money(1000),
                new Money(-4000),
                new Money(1000)
        );

        Optional<Money> reduce = monies.stream().reduce(Money::plus);
        assertThat(reduce.get().toString()).isEqualTo("-2000");
    }
}
