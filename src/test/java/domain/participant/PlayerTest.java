package domain.participant;

import domain.batting.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("플레이어는 게임이 시작 전 베팅을 진행한다.")
    void batingTest() {
        Player player = new Player("플레이어1");
        player.battingMoney(new Money(1000));
        Money money = player.getBattingMoney();

        Assertions.assertThat(money).isEqualTo(new Money(1000));
    }
}
