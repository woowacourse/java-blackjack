package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Name;
import domain.participant.Player;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class BettingBoardTest {

    @Test
    void 플레이어별_베팅_금액_등록_확인() {
        Player gorae = new Player(Name.from("고래"));
        Bet bet = new Bet(new BigDecimal("10000"));
        BettingBoard board = new BettingBoard();

        board.addBetting(gorae, bet);

        assertThat(board.getBet(gorae)).isEqualTo(bet);
    }

    @Test
    void 수익률_계산_결과_확인() {
        Player pobi = new Player(Name.from("pobi"));
        BettingBoard board = new BettingBoard();
        board.addBetting(pobi, new Bet(new BigDecimal("10000")));

        Profit profit = board.calculateProfit(pobi, new BigDecimal("1.5"));
        assertThat(profit.value()).isEqualByComparingTo(new BigDecimal("15000"));

        Profit loss = board.calculateProfit(pobi, new BigDecimal("-1.0"));
        assertThat(loss.value()).isEqualByComparingTo(new BigDecimal("-10000"));
    }
}
