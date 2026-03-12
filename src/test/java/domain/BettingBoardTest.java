package domain;

import static org.assertj.core.api.Assertions.assertThat;
import domain.participant.Name;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingBoardTest {

    @Test
    @DisplayName("플레이어별 베팅 금액을 등록하고 확인할 수 있다.")
    void 플레이어별_베팅_금액_등록_확인() {
        Player gorae = new Player(Name.from("고래"));
        Bet bet = new Bet(10000);
        BettingBoard board = new BettingBoard();

        board.addBetting(gorae, bet);

        assertThat(board.getBet(gorae)).isEqualTo(bet);
    }

    @Test
    @DisplayName("수익률에 따라 올바른 Profit 객체를 반환한다.")
    void 수익률_계산_결과_확인() {
        Player pobi = new Player(Name.from("pobi"));
        BettingBoard board = new BettingBoard();
        board.addBetting(pobi, new Bet(10000));

        Profit profit = board.calculateProfit(pobi, 1.5);
        assertThat(profit.toInt()).isEqualTo(15000);

        Profit loss = board.calculateProfit(pobi, -1.0);
        assertThat(loss.toInt()).isEqualTo(-10000);
    }
}
