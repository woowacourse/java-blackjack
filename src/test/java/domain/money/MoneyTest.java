package domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Cards;
import domain.money.Money;
import domain.status.end.BlackJack;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void create() {
        assertDoesNotThrow(() -> new Money(100));
    }

    @Test
    void multiplyProfitWeight() {
        Money weightedMoney = new Money(100)
                .multiply(new BlackJack(new Cards()).profitWeight());
        assertThat(weightedMoney.getAmount()).isEqualTo(150);
    }
}
