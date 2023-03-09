package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTableTest {

    @Test
    @DisplayName("승패 결과 저장한다.")
    void bettingTest() {
        final ResultTable resultTable = new ResultTable();
        final String name = "test";

        resultTable.put(name, GameResult.NORMAL_WIN);

        assertThat(resultTable.get(name)).isEqualTo(GameResult.NORMAL_WIN);
    }
}
