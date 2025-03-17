package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MoneyTest {
    
    @Test
    void 돈을_생성할_수_있다() {
        // expected
        assertDoesNotThrow(() -> new Money(10000));
    }
    
    @Test
    void 돈들의_합을_계산할_수_있다() {
        // given
        List<Money> amounts = List.of(new Money(10000), new Money(20000), new Money(-5000));
        
        // expected
        assertThat(Money.getTotalMoney(amounts)).isEqualTo(25000);
    }
    
    @Test
    void 돈을_곱할_수_있다() {
        // given
        Money money = new Money(10000);
        double factor = 1.5;
        Money expected = new Money(15000);
        
        // expected
        assertThat(money.multiply(factor)).isEqualTo(expected);
    }
}
