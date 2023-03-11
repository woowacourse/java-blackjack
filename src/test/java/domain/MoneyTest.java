package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.bank.Money;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/11
 */
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MoneyTest {

    @Test
    void 돈은_수익률을_받아_곱해_결과를_반환한다() {
        Money money = new Money(1000);

        double profit = 1.0;

        assertThat(money.calculateMoneyByProfit(profit)).isEqualTo(new Money(1000));
    }

}
