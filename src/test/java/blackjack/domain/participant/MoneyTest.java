package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class MoneyTest {
    @Test
    @DisplayName("Money를 생성하면, 정상 작동 한다")
    void initTest() {
        assertThatCode(() -> new Money(1000))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Money 객체가 가진 돈이 같다면, 같은 객체로 판별한다")
    void equalsTest() {
        assertThat(new Money(1000)).isEqualTo(new Money(1000));

        final HashSet<Money> moneySet = new HashSet<>();

        moneySet.add(new Money(2000));
        moneySet.add(new Money(2000));
        moneySet.add(new Money(2000));

        assertThat(moneySet.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률을 입력받으면, 현재 Money에 대한 수익금을 반환한다.")
    void calculateProfitTest() {
        Money money = new Money(1000);

        int profit = money.calculateProfit(1.5);
        assertThat(profit).isEqualTo(1500);

        profit = money.calculateProfit(1);
        assertThat(profit).isEqualTo(1000);

        profit = money.calculateProfit(0);
        assertThat(profit).isEqualTo(0);

        profit = money.calculateProfit(-1);
        assertThat(profit).isEqualTo(-1000);
    }

    @Test
    @DisplayName("현재 Money에 대한 수익을 입력한다면, 수익을 반영한 새로운 Money 객체를 반환한다.")
    void updateMoneyByProfitTest() {
        Money money = new Money(1000);

        money = money.updateMoneyByProfit(1500);
        assertThat(money).isEqualTo(new Money(2500));

        money = money.updateMoneyByProfit(-3000);
        assertThat(money).isEqualTo(new Money(-500));
    }
}
