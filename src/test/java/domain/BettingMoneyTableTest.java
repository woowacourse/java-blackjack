package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.name.Name;
import domain.user.Player;
import domain.user.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingMoneyTableTest {

    @DisplayName("특정 플레이어의 베팅 금액을 가져온다")
    @Test
    void getBettingMoneyByPlayer() {
        Player player1 = new Player(new Name("hongo"));
        Player player2 = new Player(new Name("joan"));
        Players players = Players.of(List.of(player1, player2));
        BettingMonies bettingMonies = BettingMonies.of(List.of(10000, 20000));
        BettingMoneyTable bettingMoneyTable = BettingMoneyTable.of(players, bettingMonies);
        assertThat(bettingMoneyTable.findByPlayer(player1)).isEqualTo(new BettingMoney(10000));
        assertThat(bettingMoneyTable.findByPlayer(player2)).isEqualTo(new BettingMoney(20000));
    }
}
