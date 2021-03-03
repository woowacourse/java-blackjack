package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultBoardTest {
    @DisplayName("ResultBoard 객체를 생성한다.")
    @Test
    public void createResultBoard() {
        ResultBoard resultBoard = new ResultBoard();

        assertThat(resultBoard).isInstanceOf(ResultBoard.class);
    }

    @DisplayName("사용자별로 결과를 ResultBoard 에 저장한다.")
    @Test
    public void putResultByUser() {
        Dealer dealer = new Dealer();
        Users users = new Users(Arrays.asList("amazzi", "dani", "brown"));
        ResultBoard resultBoard = new ResultBoard();
        resultBoard.putResultByUser(dealer, users);

        assertThat(resultBoard.resultBoard().size()).isEqualTo(3);
    }
}
