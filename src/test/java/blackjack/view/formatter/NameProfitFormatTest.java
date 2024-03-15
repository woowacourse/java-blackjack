package blackjack.view.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.dto.NameProfit;
import blackjack.model.betting.Money;
import blackjack.model.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameProfitFormatTest {
    @Test
    @DisplayName("플레이어의 이름과 수익을 형식에 맞게 포맷한다.")
    void format() {
        NameProfit nameProfit = new NameProfit(new Name("pobi"), new Money(10000));
        assertThat(NameProfitFormat.format(nameProfit)).isEqualTo("pobi: 10000");
    }

}
