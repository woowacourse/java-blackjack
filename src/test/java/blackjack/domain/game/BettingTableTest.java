package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Player;
import blackjack.domain.vo.Money;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BettingTableTest {

    @Test
    @DisplayName("베팅 정보를 추가한다")
    void createBettingTable() {
        BettingTable bettingTable = new BettingTable();

        bettingTable.put(new Player("박스터"), new BettingMoney(10000));

        assertThat(bettingTable.getBets()).hasSize(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"WIN:10000", "TIE:0", "LOSE:-10000", "BLACKJACK:15000"}, delimiter = ':')
    @DisplayName("수익을 계산한다")
    void calculateProfit(GameResult gameResult, int expect) {
        BettingTable bettingTable = new BettingTable();
        Player player = new Player("박스터");
        bettingTable.put(player, new BettingMoney(10000));
        BlackjackResult blackjackResult = new BlackjackResult(
                Map.of(player, gameResult)
        );

        BettingResult bettingResult = bettingTable.calculateResult(blackjackResult);

        assertThat(bettingResult.getBettingResults()).containsEntry(player, new Money(expect));
        ;
    }
}
