package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import domain.money.BettingMoney;
import domain.money.BettingMoneyTable;
import domain.money.BettingMonies;
import domain.money.Money;
import domain.name.Name;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExchangerTest {
    public static final Card ACE = new Card(Denomination.ACE, Suits.HEART);
    public static final Card SIX = new Card(Denomination.SIX, Suits.HEART);
    public static final Card EIGHT = new Card(Denomination.EIGHT, Suits.HEART);
    public static final Card NINE = new Card(Denomination.NINE, Suits.HEART);
    public static final Card TEN = new Card(Denomination.TEN, Suits.HEART);
    private final Player player = new Player(new Name("hongo"));
    private final Dealer dealer = new Dealer();
    private final Players players = Players.of(List.of(player));
    private final BettingMonies bettingMonies = BettingMonies.of(List.of(BettingMoney.of(10000)));
    private final BettingMoneyTable bettingMoneyTable = BettingMoneyTable.of(players, bettingMonies);
    private final Exchanger exchanger = new Exchanger(bettingMoneyTable);

    @DisplayName("플레이어가 블랙잭으로 승리하여 1.5배의 수익을 얻는다")
    @Test
    void getWinningMoneyOfPlayer_WhenPlayerIsBlackjackWin() {
        player.hit(ACE);
        player.hit(TEN);

        dealer.hit(TEN);
        dealer.hit(TEN);
        dealer.hit(ACE);

        assertThat(exchanger.getWinningMoneyOfPlayer(player, dealer)).isEqualTo(new Money(15000));
    }

    @DisplayName("플레이어가 승리하여 베팅 금액만큼의 수익을 얻는다")
    @Test
    void getWinningMoneyOfPlayer_WhenPlayerIsWin() {
        player.hit(NINE);
        player.hit(TEN);

        dealer.hit(EIGHT);
        dealer.hit(TEN);

        assertThat(exchanger.getWinningMoneyOfPlayer(player, dealer)).isEqualTo(new Money(10000));
    }

    @DisplayName("플레이어가 패배하여 베팅 금액만큼의 손해를 본다")
    @Test
    void getWinningMoneyOfPlayer_WhenPlayerIsLOSE() {
        player.hit(SIX);
        player.hit(TEN);

        dealer.hit(EIGHT);
        dealer.hit(TEN);

        assertThat(exchanger.getWinningMoneyOfPlayer(player, dealer)).isEqualTo(new Money(-10000));
    }

    @DisplayName("플레이어가 무승부를 하여 수익을 얻지 않는다")
    @Test
    void getWinningMoneyOfPlayer_WhenPlayerIsPush() {
        player.hit(EIGHT);
        player.hit(TEN);

        dealer.hit(EIGHT);
        dealer.hit(TEN);

        assertThat(exchanger.getWinningMoneyOfPlayer(player, dealer)).isEqualTo(new Money(0));
    }

    @DisplayName("플레이어들의 수익을 통해 딜러의 수익을 계산한다")
    @Test
    void getWinningMoneyOfDealer() {
        List<Money> monies = List.of(new Money(-10000), new Money(0), new Money(5000));
        assertThat(exchanger.getWinningMoneyOfDealer(monies)).isEqualTo(new Money(5000));
    }
}