package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinningStatusTest {
    @Test
    void 참가자가_21_초과인_경우_딜러와_무관하게_패배한다() {
        Player player = new Player("pobi");
        player.receive(new Card(Rank.TEN, Suit.HEART));
        player.receive(new Card(Rank.TEN, Suit.CLOVER));
        player.receive(new Card(Rank.TWO, Suit.HEART));

        Dealer dealer = new Dealer();

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러가_21_초과한_경우_플레이어가_승리한다() {
        Player player = new Player("pobi");
        player.receive(new Card(Rank.TEN, Suit.CLOVER));
        player.receive(new Card(Rank.TWO, Suit.HEART));

        Dealer dealer = new Dealer();
        dealer.receive(new Card(Rank.TEN, Suit.CLOVER));
        dealer.receive(new Card(Rank.TEN, Suit.HEART));
        dealer.receive(new Card(Rank.TWO, Suit.HEART));

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 딜러와_참가자의_카드의_합이_동일한_경우_무승부로_처리한다() {
        Player player = new Player("pobi");
        player.receive(new Card(Rank.TEN, Suit.CLOVER));
        player.receive(new Card(Rank.TWO, Suit.HEART));

        Dealer dealer = new Dealer();
        dealer.receive(new Card(Rank.TEN, Suit.CLOVER));
        dealer.receive(new Card(Rank.TWO, Suit.HEART));

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.TIE);
    }

    @Test
    void 딜러의_카드의_합이_참가자의_카드의_합보다_큰_경우_패배한다() {
        Player player = new Player("pobi");
        player.receive(new Card(Rank.TEN, Suit.CLOVER));
        player.receive(new Card(Rank.TWO, Suit.HEART));

        Dealer dealer = new Dealer();
        dealer.receive(new Card(Rank.TEN, Suit.CLOVER));
        dealer.receive(new Card(Rank.THREE, Suit.HEART));

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러의_카드의_합이_참가자의_카드의_합보다_작을_경우_승리한다() {
        Player player = new Player("pobi");
        player.receive(new Card(Rank.TEN, Suit.CLOVER));
        player.receive(new Card(Rank.THREE, Suit.HEART));

        Dealer dealer = new Dealer();
        dealer.receive(new Card(Rank.TEN, Suit.CLOVER));
        dealer.receive(new Card(Rank.TWO, Suit.HEART));

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.WIN);
    }
}