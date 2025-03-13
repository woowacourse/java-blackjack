package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.money.Money;

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
    
    @ParameterizedTest
    @CsvSource({"10000, 20000, false", "20000, 19999, true"})
    void 내_돈이_더_큰지_비교할_수_있다(int myMoneyAmount, int yourMoneyAmount, boolean expected) {
        // given
        Money myMoney = new Money(myMoneyAmount);
        Money yourMoney = new Money(yourMoneyAmount);
        
        // expected
        assertThat(myMoney.isGreaterThan(yourMoney)).isEqualTo(expected);
    }
    
    @ParameterizedTest
    @CsvSource({"10000, 20000, true", "20000, 19999, false"})
    void 내_돈이_더_작은지_비교할_수_있다(int myMoneyAmount, int yourMoneyAmount, boolean expected) {
        // given
        Money myMoney = new Money(myMoneyAmount);
        Money yourMoney = new Money(yourMoneyAmount);
        
        // expected
        assertThat(myMoney.isLessThan(yourMoney)).isEqualTo(expected);
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
