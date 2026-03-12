package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.game.WinningStatus;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.Test;

import static domain.BlackjackRule.DEALER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

class WinningStatusTest {

    private Player createPlayer(Rank... ranks) {
        Player player = new Player("pobi");
        for (Rank rank : ranks) {
            Card card = new Card(rank, Suit.HEART);
            player.receive(card);
        }

        return player;
    }

    private Dealer createDealer(Rank... ranks) {
        Dealer dealer = new Dealer(DEALER_NAME);
        for (Rank rank : ranks) {
            Card card = new Card(rank, Suit.HEART);
            dealer.receive(card);
        }

        return dealer;
    }

    @Test
    void 플레이어의_카드의_합이_21을_초과하는_경우_플레이어가_패배한다() {
        Player player = createPlayer(Rank.TEN, Rank.TEN, Rank.TWO);
        Dealer dealer = new Dealer(DEALER_NAME);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러의_카드의_합이_21을_초과하는_경우_플레이어가_승리한다() {
        Player player = createPlayer(Rank.TEN, Rank.TWO);
        Dealer dealer = createDealer(Rank.TEN, Rank.TEN, Rank.TWO);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 플레이어가_처음_받은_카드_두_장의_합이_21이고_딜러는_아닐_경우_플레이어가_승리한다(){
        Player player = createPlayer(Rank.TEN, Rank.ACE);
        Dealer dealer = createDealer(Rank.EIGHT, Rank.JACK, Rank.THREE);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.WIN);
    }

    @Test
    void 딜러가_처음_받은_카드_두_장의_합이_21이고_플레이어는_아닐_경우_딜러가_승리한다(){
        Player player = createPlayer(Rank.EIGHT, Rank.JACK, Rank.THREE);
        Dealer dealer = createDealer(Rank.TEN, Rank.ACE);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러와_플레이어의_카드의_합이_동일한_경우_무승부로_처리한다() {
        Player player = createPlayer(Rank.TEN, Rank.ACE);
        Dealer dealer = createDealer(Rank.TEN, Rank.ACE);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.TIE);
    }

    @Test
    void 딜러의_카드의_합이_플레이어의_카드의_합보다_큰_경우_플레이어가_패배한다() {
        Player player = createPlayer(Rank.TEN, Rank.TWO);
        Dealer dealer = createDealer(Rank.TEN, Rank.THREE);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.LOSE);
    }

    @Test
    void 딜러의_카드의_합이_플레이어의_카드의_합보다_작을_경우_플레이어가_승리한다() {
        Player player = createPlayer(Rank.TEN, Rank.THREE);
        Dealer dealer = createDealer(Rank.TEN, Rank.TWO);

        WinningStatus status = WinningStatus.of(player, dealer);

        assertThat(status).isEqualTo(WinningStatus.WIN);
    }
}
