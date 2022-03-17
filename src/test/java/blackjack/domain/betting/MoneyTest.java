package blackjack.domain.betting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class MoneyTest {

    @Test
    @DisplayName("돈을 배팅할 수 있다.")
    void bettingMoney() {
        Money money = new Money(10000);
        assertThat(money).isEqualTo(new Money(10000));
    }

    @Test
    @DisplayName("배팅금액만큼 수익을 얻을 수 있다.")
    void increaseMoney() {
        Money money = new Money(10000);
        money.win();
        assertThat(money.profit()).isEqualTo(10000);
    }

    @Test
    @DisplayName("배팅금액을 잃을 수 있다.")
    void decreaseMoney() {
        Money money = new Money(10000);
        money.lose();
        assertThat(money.profit()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("블랙잭인 경우 배팅금액의 1.5배 만큼 수익을 얻을 수 있다.")
    void increaseWhenBlackjack() {
        Money money = new Money(10000);
        money.winByBlackjack();
        assertThat(money.profit()).isEqualTo(15000);
    }

}
