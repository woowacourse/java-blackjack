package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Cards;
import domain.game.Money;
import domain.status.end.BlackJack;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void create() {
        assertAll(
                () -> assertDoesNotThrow(() -> new Money(100)),
                () -> assertThatThrownBy(() -> new Money(0))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new Money(-10))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void multiplyProfitWeight() {
        Money weightedMoney = new Money(100)
                .multiply(new BlackJack(new Cards()).profitWeight());
        assertThat(weightedMoney.getAmount()).isEqualTo(BigDecimal.valueOf(150.0));
    }
}
