package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetsTest {

    @Test
    @DisplayName("플레이어의 배팅을 저장하고, 입력된 순서를 보장하여 반환한다.")
    void playerBet_And_getBets_PreservesOrder() {
        Bets bets = new Bets();
        Player pobi = new Player("pobi");
        Player jason = new Player("jason");
        bets.playerBet(pobi, 1000);
        bets.playerBet(jason, 2000);

        assertThat(bets.getBets()).extracting(entry -> entry.getKey().getNickname())
                .containsExactly("pobi", "jason");
    }

    @Test
    @DisplayName("getBets로 반환된 컬렉션을 외부에서 수정하려 하면 예외가 발생한다.")
    void getBets_ReturnsUnmodifiableSet() {
        Bets bets = new Bets();
        bets.playerBet(new Player("pobi"), 1000);

        assertThatThrownBy(() -> bets.getBets().clear())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 블랙잭 승리 배당(1.5배)을 받는다.")
    void determinePlayerProfits_WinBlackjack() {
        Bets bets = new Bets();
        Player player = new Player("pobi");
        player.receiveCard(new Card(Rank.ACE, Suit.SPADES));
        player.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        bets.playerBet(player, 10000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.NINE, Suit.SPADES));
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADES));

        Map<Player, Integer> profits = bets.determinePlayerProfits(dealer);

        assertThat(profits).containsEntry(player, 15000);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 블랙잭이면 무승부 배당을 받는다.")
    void determinePlayerProfits_DrawBlackjack() {
        Bets bets = new Bets();
        Player player = new Player("pobi");
        player.receiveCard(new Card(Rank.ACE, Suit.SPADES));
        player.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        bets.playerBet(player, 10000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.ACE, Suit.HEARTS));
        dealer.receiveCard(new Card(Rank.TEN, Suit.HEARTS));

        Map<Player, Integer> profits = bets.determinePlayerProfits(dealer);

        assertThat(profits.get(player)).isZero();
    }

    @Test
    @DisplayName("플레이어가 버스트되면 딜러의 상태(버스트 여부)와 무관하게 무조건 패배 배당을 받는다.")
    void determinePlayerProfits_LoseWhenPlayerBust() {
        Bets bets = new Bets();
        Player player = new Player("pobi");
        player.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        player.receiveCard(new Card(Rank.TEN, Suit.HEARTS));
        player.receiveCard(new Card(Rank.TWO, Suit.CLUBS));
        bets.playerBet(player, 10000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.DIAMONDS));
        dealer.receiveCard(new Card(Rank.SIX, Suit.CLUBS));
        dealer.receiveCard(new Card(Rank.SIX, Suit.HEARTS));

        Map<Player, Integer> profits = bets.determinePlayerProfits(dealer);

        assertThat(profits).containsEntry(player, -10000);
    }

    @Test
    @DisplayName("플레이어가 버스트되지 않고 딜러가 버스트되면 승리 배당을 받는다.")
    void determinePlayerProfits_WinWhenDealerBust() {
        Bets bets = new Bets();
        Player player = new Player("pobi");
        player.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        player.receiveCard(new Card(Rank.NINE, Suit.HEARTS));
        bets.playerBet(player, 10000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.DIAMONDS));
        dealer.receiveCard(new Card(Rank.SIX, Suit.CLUBS));
        dealer.receiveCard(new Card(Rank.SIX, Suit.HEARTS));

        Map<Player, Integer> profits = bets.determinePlayerProfits(dealer);

        assertThat(profits).containsEntry(player, 10000);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트되지 않은 상태에서 플레이어의 점수가 더 높으면 승리 배당을 받는다.")
    void determinePlayerProfits_WinWhenScoreHigher() {
        Bets bets = new Bets();
        Player player = new Player("pobi");
        player.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        player.receiveCard(new Card(Rank.NINE, Suit.HEARTS));
        bets.playerBet(player, 10000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.DIAMONDS));
        dealer.receiveCard(new Card(Rank.EIGHT, Suit.CLUBS));

        Map<Player, Integer> profits = bets.determinePlayerProfits(dealer);

        assertThat(profits).containsEntry(player, 10000);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트되지 않은 상태에서 점수가 같으면 무승부 배당을 받는다.")
    void determinePlayerProfits_DrawWhenScoreSame() {
        Bets bets = new Bets();
        Player player = new Player("pobi");
        player.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        player.receiveCard(new Card(Rank.NINE, Suit.HEARTS));
        bets.playerBet(player, 10000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.DIAMONDS));
        dealer.receiveCard(new Card(Rank.NINE, Suit.CLUBS));

        Map<Player, Integer> profits = bets.determinePlayerProfits(dealer);

        assertThat(profits.get(player)).isZero();
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트되지 않은 상태에서 플레이어의 점수가 더 낮으면 패배 배당을 받는다.")
    void determinePlayerProfits_LoseWhenScoreLower() {
        Bets bets = new Bets();
        Player player = new Player("pobi");
        player.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        player.receiveCard(new Card(Rank.EIGHT, Suit.HEARTS));
        bets.playerBet(player, 10000);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.DIAMONDS));
        dealer.receiveCard(new Card(Rank.NINE, Suit.CLUBS));

        Map<Player, Integer> profits = bets.determinePlayerProfits(dealer);

        assertThat(profits).containsEntry(player, -10000);
    }
}
