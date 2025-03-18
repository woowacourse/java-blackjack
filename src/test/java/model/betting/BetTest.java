package model.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

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
        Bet bet = new Bet(new Money(money), player);

        //then
        assertThat(bet.equals(new Bet(new Money(money), player))).isTrue();
    }

    @Test
    @DisplayName("플레이어의 돈을 1.5배로 만든다.")
    void 플레이어의_돈을_일점오배로_만든다() {
        //given
        Player player = new Player("moda");
        int money = 10000;
        Bet bet = new Bet(new Money(money), player);

        //when
        Bet increasedBet = bet.increase(new IncreasingRate(1.5));

        //then
        assertThat(increasedBet.equals(new Bet(new Money(15000), player))).isTrue();
    }

    @Test
    @DisplayName("배팅금액의 소유자가 플레이어에서 딜러로 변화한다.")
    void 배팅금액의_소유자가_플레이어에서_딜러로_변화() {
        //given
        Player player = new Player("moda");
        Dealer dealer = new Dealer();
        int money = 10000;
        Bet bet = new Bet(new Money(money), player);

        //when
        Bet newBet = bet.changeOwnerTo(dealer);

        //then
        assertThat(newBet.getOwner()).isEqualTo(dealer);
    }

    @Test
    @DisplayName("베팅을 제시한 사람이 맞는지 확인한다.")
    void 베팅을_제시한_사람이_맞는_확인() {
        //given
        Player player = new Player("moda");
        Dealer dealer = new Dealer();
        Bet bet = new Bet(new Money(10000), player, dealer);

        //when
        boolean same = bet.betterEquals(player);

        //then
        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("베팅의 소유자가 다를 때 베팅을 제시한 사람 기준 수익금을 계산한다.")
    void 소유자가_다를때_베팅을_제시한_사람_기준_수익금_계산() {
        //given
        Player player = new Player("moda");
        Dealer dealer = new Dealer();
        Bet bet = new Bet(new Money(10000), player, dealer);

        //when
        int betterRevenue = bet.calculateBetterRevenue();

        //then
        assertThat(betterRevenue).isEqualTo(-10000);
    }

    @Test
    @DisplayName("베팅의 소유자가 다를 때 베팅을 소유한 사람 기준 수익금을 계산한다.")
    void 소유자가_다를때_베팅을_소유한_사람_기준_수익금_계산() {
        //given
        Player player = new Player("moda");
        Dealer dealer = new Dealer();
        Bet bet = new Bet(new Money(10000), player, dealer);

        //when
        int ownerRevenue = bet.calculateOwnerRevenue();

        //then
        assertThat(ownerRevenue).isEqualTo(10000);
    }

    @Test
    @DisplayName("베팅의 소유자와 베팅을 제시한 사람이 같을 때 수익금을 계산한다.")
    void 소유자가_같을때_수익금_계산() {
        //given
        Player player = new Player("moda");
        Bet bet = new Bet(new Money(10000), player, player);

        //when
        int ownerRevenue = bet.calculateOwnerRevenue();
        int betterRevenue = bet.calculateBetterRevenue();

        //then
        assertThat(ownerRevenue).isEqualTo(10000);
        assertThat(betterRevenue).isEqualTo(10000);
    }
}
