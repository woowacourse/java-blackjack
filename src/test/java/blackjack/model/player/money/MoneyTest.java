package blackjack.model.player.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void 초기화를_0원으로_할_수_있다() {
        Money money = Money.zero();

        assertThat(money.value).isEqualTo(0);
    }

    @Test
    void 베팅_금액을_받아_금액을_더한다() {
        Money money = new Money(1000);

        money.add(500);

        assertThat(money.value).isEqualTo(1500);
    }

    @Test
    void 베팅_금액을_받아_금액을_뺀다() {
        Money money = new Money(1000);

        money.subtract(500);

        assertThat(money.value).isEqualTo(500);
    }
}
