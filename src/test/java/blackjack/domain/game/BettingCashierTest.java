package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Symbol;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BettingCashierTest {

    private List<Player> players;
    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        Player winner = Player.nameOf("win");
        Player loser = Player.nameOf("lose");
        Player tier = Player.nameOf("tie");

        distributeCards(winner, loser, tier);
        players = List.of(winner, loser, tier);

        dealer = new Dealer();
        dealer.receive(new Card(Rank.SEVEN, Symbol.DIAMOND));
    }

    private void distributeCards(Player winner, Player loser, Player tier) {
        winner.receive(new Card(Rank.KING, Symbol.CLUB));
        loser.receive(new Card(Rank.TWO, Symbol.CLUB));
        tier.receive(new Card(Rank.SEVEN, Symbol.CLUB));
    }

    @Test
    @DisplayName("승자는 해당 금액만큼의 수익을 얻는다")
    void findProfitOf_Winner() {
        Player winner = players.get(0);
        Betting betting = new Betting();
        betting.bet(winner, 10000);

        BettingCashier cashier = new BettingCashier(betting, Result.of(players, dealer));
        assertThat(cashier.findProfitOf(winner)).isEqualTo(10000);
    }

    @Test
    @DisplayName("블랙잭으로 이기면 해당 금액의 1.5배만큼의 수익을 얻는다")
    void findProfitOf_BlackjackWinner() {
        Player blackjackWinner = Player.nameOf("name");
        blackjackWinner.receive(List.of(
                new Card(Rank.KING, Symbol.CLUB),
                new Card(Rank.ACE, Symbol.CLUB)));

        Betting betting = new Betting();
        betting.bet(blackjackWinner, 10000);

        BettingCashier cashier = new BettingCashier(betting, Result.of(List.of(blackjackWinner), new Dealer()));
        assertThat(cashier.findProfitOf(blackjackWinner)).isEqualTo(15000);
    }

    @Test
    @DisplayName("패자는 해당 금액만큼의 수익을 잃는다")
    void findProfitOf_Loser() {
        Player loser = players.get(1);
        Betting betting = new Betting();
        betting.bet(loser, 10000);

        BettingCashier cashier = new BettingCashier(betting, Result.of(players, dealer));
        assertThat(cashier.findProfitOf(loser)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("무승부는 수익이 없다(0원 이다)")
    void findProfitOf_Tier() {
        Player tier = players.get(2);
        Betting betting = new Betting();
        betting.bet(tier, 10000);

        BettingCashier cashier = new BettingCashier(betting, Result.of(players, dealer));
        assertThat(cashier.findProfitOf(tier)).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러는 모든 참가자 수익만큼 돈을 잃는다")
    void findProfitOfDealer() {
        Player winner = players.get(0);
        Player loser = players.get(1);
        Player tier = players.get(2);
        Betting betting = new Betting();
        betting.bet(winner, 10000);
        betting.bet(loser, 5000);
        betting.bet(tier, 100);

        BettingCashier cashier = new BettingCashier(betting, Result.of(players, dealer));
        assertThat(cashier.findProfitOfDealer()).isEqualTo(-5000);
    }

}