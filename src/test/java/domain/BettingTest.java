package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BettingTest {
    @Test
    void 참가자_카드가_21을_초과하는_경우_배팅_금액을_잃는다() {
        Player player = new Player("pobi");
        player.bet(10000);
        Dealer dealer = new Dealer();

        player.receive(new Card(Rank.TEN, Suit.HEART));
        player.receive(new Card(Rank.TEN, Suit.CLOVER));
        player.receive(new Card(Rank.FIVE, Suit.SPADE));

        WinningStatus status = WinningStatus.of(player, dealer);
        player.applyRoundResult(status);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
        assertThat(player.profit()).isEqualTo(-10000);
    }

    @Test
    void 처음_카드가_블랙잭이고_딜러가_블랙잭이면_원금을_받는다() {
        Player player = new Player("pobi");
        player.bet(10000);
        Dealer dealer = new Dealer();

        player.receiveInitialCards(List.of(new Card(Rank.TEN, Suit.SPADE), new Card(Rank.ACE, Suit.HEART)));
        dealer.receiveInitialCards(List.of(new Card(Rank.TEN, Suit.SPADE), new Card(Rank.ACE, Suit.HEART)));

        WinningStatus status = WinningStatus.of(player, dealer);
        player.applyRoundResult(status);

        assertThat(status).isEqualTo(WinningStatus.TIE);
        assertThat(player.profit()).isEqualTo(0);
    }

    @Test
    void 처음_카드가_블랙잭이고_딜러가_블랙잭이_아니면_특정_배율로_받는다() {
        Player player = new Player("pobi");
        player.bet(10000);
        Dealer dealer = new Dealer();

        player.receiveInitialCards(List.of(new Card(Rank.TEN, Suit.SPADE), new Card(Rank.ACE, Suit.HEART)));
        dealer.receiveInitialCards(List.of(new Card(Rank.TEN, Suit.SPADE), new Card(Rank.EIGHT, Suit.HEART)));

        WinningStatus status = WinningStatus.of(player, dealer);
        player.applyRoundResult(status);

        assertThat(status).isEqualTo(WinningStatus.BLACKJACK_WIN);
        assertThat(player.profit()).isEqualTo(15000);
    }

    @Test
    void 참가자_카드의_합이_딜러_카드합보다_작은_경우_배팅_금액를_잃는다() {
        Player player = new Player("pobi");
        player.bet(10000);
        Dealer dealer = new Dealer();

        player.receive(new Card(Rank.TEN, Suit.HEART));
        player.receive(new Card(Rank.FIVE, Suit.SPADE));
        dealer.receiveInitialCards(List.of(new Card(Rank.TEN, Suit.SPADE), new Card(Rank.EIGHT, Suit.HEART)));

        WinningStatus status = WinningStatus.of(player, dealer);
        player.applyRoundResult(status);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
        assertThat(player.profit()).isEqualTo(-10000);
    }

    @Test
    void 참가자_카드의_합이_딜러_합보다_큰_켱우_배팅_금액만큼_받는다() {
        Player player = new Player("pobi");
        player.bet(10000);
        Dealer dealer = new Dealer();

        player.receive(new Card(Rank.TEN, Suit.HEART));
        player.receive(new Card(Rank.NINE, Suit.SPADE));
        dealer.receiveInitialCards(List.of(new Card(Rank.TEN, Suit.SPADE), new Card(Rank.EIGHT, Suit.HEART)));

        WinningStatus status = WinningStatus.of(player, dealer);
        player.applyRoundResult(status);

        assertThat(status).isEqualTo(WinningStatus.WIN);
        assertThat(player.profit()).isEqualTo(10000);
    }

    @Test
    void 딜러가_블랙잭을_초과하면_플레이어는_베팅_금액을_받는다() {
        Player player1 = new Player("pobi");
        Player player2 = new Player("pobi");
        player1.bet(10000);
        player2.bet(20000);
        Dealer dealer = new Dealer();

        player1.receive(new Card(Rank.TEN, Suit.HEART));
        player1.receive(new Card(Rank.NINE, Suit.SPADE));
        player2.receive(new Card(Rank.TEN, Suit.SPADE));
        player2.receive(new Card(Rank.TEN, Suit.SPADE));
        player2.receive(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveInitialCards(List.of(new Card(Rank.TEN, Suit.SPADE), new Card(Rank.EIGHT, Suit.HEART)));
        dealer.receive(new Card(Rank.NINE, Suit.HEART));

        WinningStatus status1 = WinningStatus.of(player1, dealer);
        WinningStatus status2 = WinningStatus.of(player2, dealer);
        player1.applyRoundResult(status1);
        player2.applyRoundResult(status2);

        assertThat(status1).isEqualTo(WinningStatus.WIN);
        assertThat(status2).isEqualTo(WinningStatus.LOSE);
        assertThat(player1.profit()).isEqualTo(10000);
        assertThat(player2.profit()).isEqualTo(-20000);
    }
}
