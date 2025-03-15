package model.betting;

import static org.assertj.core.api.Assertions.assertThat;

import model.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetTest {

    @Test
    @DisplayName("플레이어가 내기에 걸 돈을 제시하면 이를 저장한다.")
    void betMoney() {
        //given
        Player player = new Player("moda");
        int money = 10000;

        //when
        Bet bet = new Bet(money, player);

        //then
        assertThat(bet.equals(new Bet(10000, player))).isTrue();
    }
}