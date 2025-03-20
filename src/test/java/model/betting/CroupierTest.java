package model.betting;

import static org.assertj.core.api.Assertions.assertThat;

import model.participant.Dealer;
import model.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CroupierTest {
    private Croupier croupier;

    @BeforeEach
    void setUp() {
        croupier = new Croupier();
    }

    @DisplayName("플레이어가 제시한 배팅 금액을 딜러가 보관한다.")
    @Test
    void 플레이어_제시_배팅금액을_저장() {
        //given
        int money = 10000;
        Player better = new Player("moda");
        Bet bet = new Bet(new Money(money), better);

        //when
        croupier.receiveBet(bet);

        //then
        assertThat(bet.getOwner()).isEqualTo(better);
    }

    @DisplayName("패배한 플레이어의 베팅금액을 딜러의 것으로 만든다.")
    @Test
    void 패배한_플레이어의_배팅금액은_딜러의_것이_된다() {
        //given
        Player pobi = new Player("pobi");
        croupier.receiveBet(new Bet(new Money(10000), pobi));
        Player jason = new Player("jason");
        croupier.receiveBet(new Bet(new Money(20000), jason));
        Dealer dealer = new Dealer();

        //when
        croupier.updateBetOwnerFrom(jason, dealer);

        //then
        assertThat(croupier.calculateRevenueByBetter(pobi)).isEqualTo(10000);
        assertThat(croupier.calculateRevenueByBetter(jason)).isEqualTo(-20000);
    }

    @DisplayName("10000원을 배팅한 플레이어는 승리하고, 20000원을 배팅한 플레이어는 패배했을 때 딜러는 10000원의 수익이 발생한다.")
    @Disabled
    @Test
    void 딜러는_10000원의_수익이_발생한다() {
        //given
        Player pobi = new Player("pobi");
        croupier.receiveBet(new Bet(new Money(10000), pobi));
        Player jason = new Player("jason");
        croupier.receiveBet(new Bet(new Money(20000), jason));
        Dealer dealer = new Dealer();

        //when
        croupier.updateBetOwnerFrom(jason, dealer);

        //then
        assertThat(croupier.calculateRevenueByAllBetters()).isEqualTo(-10000);
    }

    @DisplayName("10000원을 배팅한 플레이어는 패배하고, 20000원을 배팅한 플레이어는 승리했을 때 딜러는 -10000원의 수익이 발생한다.")
    @Test
    void 딜러는_마이너스10000원의_수익이_발생한다() {
        //given
        Player pobi = new Player("pobi");
        Player jason = new Player("jason");
        croupier.receiveBet(new Bet(new Money(10000), pobi));
        croupier.receiveBet(new Bet(new Money(20000), jason));
        Dealer dealer = new Dealer();

        //when
        croupier.updateBetOwnerFrom(pobi, dealer);

        //then
        assertThat(croupier.calculateRevenueByAllBetters()).isEqualTo(10000);
    }

}