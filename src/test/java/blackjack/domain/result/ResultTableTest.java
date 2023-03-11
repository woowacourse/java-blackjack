package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ResultTableTest {

    @Test
    @DisplayName("승패 결과 저장한다.")
    void bettingTest() {
        final ResultTable resultTable = new ResultTable();
        final String name = "test";

        resultTable.put(name, GameResult.NORMAL_WIN);

        assertThat(resultTable.get(name)).isEqualTo(GameResult.NORMAL_WIN);
    }

    @Test
    @DisplayName("없는 사용자의 결과 요청시 예외를 발생시킨다.")
    void userNameExceptionTest() {
        final ResultTable resultTable = new ResultTable();
        final String userName = "test1";
        final String wrongUserName = "test2";

        resultTable.put(userName, GameResult.NORMAL_WIN);

        assertThatThrownBy(() -> resultTable.get(wrongUserName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ResultTable.USER_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}
