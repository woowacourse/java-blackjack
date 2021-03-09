package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultBoardTest {
    @DisplayName("ResultBoard 객체를 생성한다.")
    @Test
    public void createResultBoard() {
        Dealer dealer = new Dealer();
        ResultBoard resultBoard = new ResultBoard(dealer, Arrays.asList(
                new Player("amazzi"),
                new Player("dani"),
                new Player("pobi")
        ));

        assertThat(resultBoard).isInstanceOf(ResultBoard.class);
    }
}
