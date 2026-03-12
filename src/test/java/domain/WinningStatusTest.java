package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinningStatusTest {
    @Test
    void 참가자가_21_초과인_경우_딜러와_무관하게_패배한다() {
        Player player = createPlayer(Rank.TEN, Rank.TEN, Rank.TWO);
        Dealer dealer = new Dealer();

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러가_21_초과한_경우_플레이어가_승리한다() {
        Player player = createPlayer(Rank.TEN, Rank.TWO);
        Dealer dealer = createDealer(Rank.TEN, Rank.TEN, Rank.TWO);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 딜러와_참가자의_카드의_합이_동일한_경우_무승부로_처리한다() {
        Player player = createPlayer(Rank.TEN, Rank.TWO);
        Dealer dealer = createDealer(Rank.TEN, Rank.TWO);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.TIE);
    }

    @Test
    void 딜러의_카드의_합이_참가자의_카드의_합보다_큰_경우_패배한다() {
        Player player = createPlayer(Rank.TEN, Rank.TWO);
        Dealer dealer = createDealer(Rank.TEN, Rank.THREE);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러의_카드의_합이_참가자의_카드의_합보다_작을_경우_승리한다() {
        Player player = createPlayer(Rank.TEN, Rank.THREE);
        Dealer dealer = createDealer(Rank.TEN, Rank.TWO);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 처음_두_장이_아닌_21점은_블랙잭_승리가_아니다() {
        Player player = createPlayer(Rank.ACE, Rank.NINE, Rank.ACE);
        Dealer dealer = createDealer(Rank.TEN, Rank.EIGHT);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.WIN);
    }

    private Player createPlayer(Rank... ranks) {
        Player player = new Player("pobi");
        for (Rank rank : ranks) {
            Card card = new Card(rank, Suit.HEART);
            player.receive(card);
        }

        return player;
    }

    private Dealer createDealer(Rank... ranks) {
        Dealer dealer = new Dealer();
        for (Rank rank : ranks) {
            Card card = new Card(rank, Suit.HEART);
            dealer.receive(card);
        }

        return dealer;
    }
}
