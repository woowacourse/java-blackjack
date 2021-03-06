package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultBoardTest {
    @DisplayName("ResultBoard 객체를 생성한다.")
    @Test
    public void createResultBoard() {
        Dealer dealer = new Dealer();
        Players players = new Players(Arrays.asList("amazzi", "dani", "brown"));
        ResultBoard resultBoard = new ResultBoard(dealer, players);

        assertThat(resultBoard).isInstanceOf(ResultBoard.class);
    }
}
