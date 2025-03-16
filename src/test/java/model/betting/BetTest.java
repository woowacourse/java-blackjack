package model.betting;

import static org.assertj.core.api.Assertions.assertThat;

import model.participant.Dealer;
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

    @Test
    @DisplayName("배팅금액의 소유자가 플레이어에서 딜러로 변화한다.")
    void 배팅금액의_소유자가_플레이어에서_딜러로_변화() {
        //given
        Player player = new Player("moda");
        Dealer dealer = new Dealer();
        int money = 10000;
        Bet bet = new Bet(money, player);

        //when
        Bet newBet = bet.changeOwnerTo(dealer);

        //then
        assertThat(newBet.getOwner()).isEqualTo(dealer);
    }


}