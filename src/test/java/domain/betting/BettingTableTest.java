package domain.betting;


import domain.gamer.Player;
import domain.gamer.PlayerName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BettingTableTest {

    Money thousandWon = Money.from("1000");
    Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = Player.from(new PlayerName("test"));
    }

    @Test
    void 베팅테이블에_베팅금액을_올린다(){
        BettingTable bettingTable = new BettingTable();

        bettingTable.bet(testPlayer, thousandWon);
        Money actualPlayerMoney = bettingTable.getPlayerProfit(testPlayer);
        Money expectedPlayerMoney = thousandWon.getMoney();

        Assertions.assertThat(actualPlayerMoney)
                .isEqualTo(expectedPlayerMoney);
    }

}
