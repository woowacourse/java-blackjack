package model.betting;

import static org.assertj.core.api.Assertions.assertThat;

import model.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetTest {

    @Test
    @DisplayName("플레이어가 내기에 걸 돈을 제시하면 이를 저장한다.")
    void 플레이어가_돈을_제시하면_저장한다() {
        //given
        Player player = new Player("moda");
        int money = 10000;

        //when
        Bet bet = new Bet(money, player);

        //then
        assertThat(bet.equals(new Bet(money, player))).isTrue();
    }

    @Test
    @DisplayName("플레이어의 돈을 1.5배로 만든다.")
    void 플레이어의_돈을_일점오배로_만든다() {
        //given
        Player player = new Player("moda");
        int money = 10000;
        Bet bet = new Bet(money, player);

        //when
        Bet increasedBet = bet.increase(1.5);

        //then
        assertThat(increasedBet.equals(new Bet(15000, player))).isTrue();
    }
}