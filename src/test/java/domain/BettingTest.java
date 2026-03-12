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
}
