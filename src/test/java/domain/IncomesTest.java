package domain;

import domain.participant.Name;
import domain.result.Income;
import domain.result.Incomes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class IncomesTest {

    @Test
    @DisplayName("딜러의 최종 수익을 반환한다.")
    void getDealerIncome() {
        Incomes positiveIncome = new Incomes(Map.of(new Name("capy1"), new Income(-1000), new Name("capy2"), new Income(-2000)));
        Incomes negativeIncome = new Incomes(Map.of(new Name("capy1"), new Income(1000), new Name("capy2"), new Income(2000)));

        assertThat(positiveIncome.getDealerIncome()).isEqualTo(3000);
        assertThat(negativeIncome.getDealerIncome()).isEqualTo(-3000);
    }
}
