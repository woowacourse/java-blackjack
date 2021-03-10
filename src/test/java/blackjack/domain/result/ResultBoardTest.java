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
        Players players = Players.of(Arrays.asList("amazzi", "dani", "brown"));
        ResultBoard resultBoard = ResultBoard.of(players, dealer);

        assertThat(resultBoard).isInstanceOf(ResultBoard.class);
    }
}
