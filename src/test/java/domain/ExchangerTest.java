package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.bettingMoney.BettingMoneyTable;
import domain.bettingMoney.Money;
import domain.bettingMoney.Monies;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import domain.name.Name;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExchangerTest {
    /*
  - [ ] 수익 계산
  - [ ] 플레이어의 핸드가 `Blackjack`인 상태로 승리했다면, 플레이어는 베팅한 금액의 1.5배인 수익을 얻는다
  - [ ] 플레이어의 핸드가 `Blackjack`이 아닌 상태로 승리했다면, 플레이어는 베팅한 금액만큼의 수익을 얻는다
  - [ ] 플레이어와 딜러가 무승부이면 아무도 수익을 얻지 않는다
  - [ ] 플레이어가 패배했다면, 플레이어는 베팅한 금액만큼 돈을 잃는다
  - [ ] 딜러는 플레이어가 수익을 얻은 만큼 돈을 잃고, 플레이어가 돈을 잃은 만큼 수익을 얻는다
     */
    private final Player player = new Player(new Name("hongo"));
    private final Dealer dealer = new Dealer();
    private final Players players = Players.of(List.of(player));
    private final Monies monies = Monies.of(List.of(10000));
    private final BettingMoneyTable bettingMoneyTable = BettingMoneyTable.of(players, monies);
    private final Exchanger exchanger = new Exchanger(bettingMoneyTable);

    @DisplayName("플레이어가 블랙잭으로 승리하여 1.5배의 수익을 얻는다")
    @Test
    void getWinningMoney_WhenPlayerIsBlackjackWin(){
        player.hit(new Card(Denomination.ACE, Suits.HEART));
        player.hit(new Card(Denomination.TEN, Suits.HEART));

        dealer.hit(new Card(Denomination.TEN, Suits.HEART));
        dealer.hit(new Card(Denomination.TEN, Suits.SPADE));
        dealer.hit(new Card(Denomination.ACE, Suits.HEART));

        assertThat(exchanger.getPlayerWinningMoney(player, dealer)).isEqualTo(new Money(15000));
    }

    @DisplayName("플레이어가 승리하여 베팅 금액만큼의 수익을 얻는다")
    @Test
    void getWinningMoney_WhenPlayerIsWin(){
        player.hit(new Card(Denomination.NINE, Suits.HEART));
        player.hit(new Card(Denomination.TEN, Suits.HEART));

        dealer.hit(new Card(Denomination.EIGHT, Suits.HEART));
        dealer.hit(new Card(Denomination.TEN, Suits.SPADE));

        assertThat(exchanger.getPlayerWinningMoney(player, dealer)).isEqualTo(new Money(10000));
    }

    @DisplayName("플레이어가 패배하여 베팅 금액만큼의 손해를 본다")
    @Test
    void getWinningMoney_WhenPlayerIsLOSE(){
        player.hit(new Card(Denomination.SIX, Suits.HEART));
        player.hit(new Card(Denomination.TEN, Suits.HEART));

        dealer.hit(new Card(Denomination.EIGHT, Suits.HEART));
        dealer.hit(new Card(Denomination.TEN, Suits.SPADE));

        assertThat(exchanger.getPlayerWinningMoney(player, dealer)).isEqualTo(new Money(-10000));
    }

    @DisplayName("플레이어가 무승부를 하여 수익을 얻지 않는다")
    @Test
    void getWinningMoney_WhenPlayerIsPush(){
        player.hit(new Card(Denomination.EIGHT, Suits.HEART));
        player.hit(new Card(Denomination.TEN, Suits.HEART));

        dealer.hit(new Card(Denomination.EIGHT, Suits.HEART));
        dealer.hit(new Card(Denomination.TEN, Suits.SPADE));

        assertThat(exchanger.getPlayerWinningMoney(player, dealer)).isEqualTo(new Money(0));
    }
}
