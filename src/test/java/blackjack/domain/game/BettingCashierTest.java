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
        Player loser = players.get(0);
        Betting betting = new Betting();
        betting.bet(loser, 10000);

        BettingCashier cashier = new BettingCashier(betting, Result.of(players, dealer));
        assertThat(cashier.findProfitOf(loser)).isEqualTo(10000);
    }

    @Test
    @DisplayName("패자는 해당 금액만큼의 수익을 잃는다")
    void findProfitOf_Loser() {
        Player tier = players.get(1);
        Betting betting = new Betting();
        betting.bet(tier, 10000);

        BettingCashier cashier = new BettingCashier(betting, Result.of(players, dealer));
        assertThat(cashier.findProfitOf(tier)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("무승부는 수익이 없다(0원 이다)")
    void findProfitOf_Tier() {
        Player winner = players.get(2);
        Betting betting = new Betting();
        betting.bet(winner, 10000);

        BettingCashier cashier = new BettingCashier(betting, Result.of(players, dealer));
        assertThat(cashier.findProfitOf(winner)).isEqualTo(0);
    }
}