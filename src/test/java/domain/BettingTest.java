package domain;

import static domain.card.Rank.ACE;
import static domain.card.Rank.EIGHT;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.NINE;
import static domain.card.Rank.TEN;
import static domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.Test;

public class BettingTest {
    @Test
    void 참가자_카드가_21을_초과하는_경우_배팅_금액을_잃는다() {
        Player player = createPlayer(10000, TEN, TEN, FIVE);
        Dealer dealer = createDealer();

        WinningStatus status = WinningStatus.of(player, dealer);
        player.applyRoundResult(status);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
        assertThat(player.profit()).isEqualTo(-10000);
    }

    @Test
    void 처음_카드가_블랙잭이고_딜러가_블랙잭이면_원금을_받는다() {
        Player player = createPlayer(10000, TEN, ACE);
        Dealer dealer = createDealer(TEN, ACE);

        WinningStatus status = WinningStatus.of(player, dealer);
        player.applyRoundResult(status);

        assertThat(status).isEqualTo(WinningStatus.TIE);
        assertThat(player.profit()).isEqualTo(0);
    }

    @Test
    void 처음_카드가_블랙잭이고_딜러가_블랙잭이_아니면_특정_배율로_받는다() {
        Player player = createPlayer(10000, TEN, ACE);
        Dealer dealer = createDealer(TEN, EIGHT);

        WinningStatus status = WinningStatus.of(player, dealer);
        player.applyRoundResult(status);

        assertThat(status).isEqualTo(WinningStatus.BLACKJACK_WIN);
        assertThat(player.profit()).isEqualTo(15000);
    }

    @Test
    void 참가자_카드의_합이_딜러_카드합보다_작은_경우_배팅_금액를_잃는다() {
        Player player = createPlayer(10000, TEN, FIVE);
        Dealer dealer = createDealer(TEN, EIGHT);

        WinningStatus status = WinningStatus.of(player, dealer);
        player.applyRoundResult(status);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
        assertThat(player.profit()).isEqualTo(-10000);
    }

    @Test
    void 참가자_카드의_합이_딜러_합보다_큰_켱우_배팅_금액만큼_받는다() {
        Player player = createPlayer(10000, TEN, NINE);
        Dealer dealer = createDealer(TEN, EIGHT);

        WinningStatus status = WinningStatus.of(player, dealer);
        player.applyRoundResult(status);

        assertThat(status).isEqualTo(WinningStatus.WIN);
        assertThat(player.profit()).isEqualTo(10000);
    }

    @Test
    void 처음_두_장이_아닌_블랙잭이면_일반_승리_배당을_받는다() {
        Player player = createPlayer(10000, ACE, NINE, ACE);
        Dealer dealer = createDealer(TEN, EIGHT);

        WinningStatus status = WinningStatus.of(player, dealer);
        player.applyRoundResult(status);

        assertThat(status).isEqualTo(WinningStatus.WIN);
        assertThat(player.profit()).isEqualTo(10000);
    }

    @Test
    void 딜러가_블랙잭을_초과하면_플레이어는_베팅_금액을_받는다() {
        Player player1 = createPlayer(10000, TEN, NINE);
        Player player2 = createPlayer(20000, TEN, TEN, TEN);
        Dealer dealer = createDealer(TEN, EIGHT, TEN);

        WinningStatus status1 = WinningStatus.of(player1, dealer);
        WinningStatus status2 = WinningStatus.of(player2, dealer);
        player1.applyRoundResult(status1);
        player2.applyRoundResult(status2);

        assertThat(status1).isEqualTo(WinningStatus.WIN);
        assertThat(status2).isEqualTo(WinningStatus.LOSE);
        assertThat(player1.profit()).isEqualTo(10000);
        assertThat(player2.profit()).isEqualTo(-20000);
    }

    private Player createPlayer(int amount, Rank... ranks) {
        Player player = new Player("pobi");
        player.bet(amount);

        for (Rank rank : ranks) {
            player.receive(new Card(rank, HEART));
        }

        return player;
    }

    private Dealer createDealer(Rank... ranks) {
        Dealer dealer = new Dealer();
        for (Rank rank : ranks) {
            dealer.receive(new Card(rank, HEART));
        }

        return dealer;
    }
}
