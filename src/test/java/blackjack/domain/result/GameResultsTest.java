package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultsTest {

    @Test
    @DisplayName("플레이어가 승리하면 딜러 결과에 패가 1개 집계된다")
    void calculate_dealerLoseCount_whenPlayerWins() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.NINE));

        GameResults results = GameResults.calculate(new Players(List.of(player)), dealer);

        assertThat(results.getDealerResults().get(GameResult.LOSE)).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어가 패배하면 딜러 결과에 승이 1개 집계된다")
    void calculate_dealerWinCount_whenPlayerLoses() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.NINE));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        GameResults results = GameResults.calculate(new Players(List.of(player)), dealer);

        assertThat(results.getDealerResults().get(GameResult.WIN)).isEqualTo(1);
    }

    @Test
    @DisplayName("점수가 같으면 딜러 결과에 무승부가 1개 집계된다")
    void calculate_dealerDrawCount_whenScoresAreEqual() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        GameResults results = GameResults.calculate(new Players(List.of(player)), dealer);

        assertThat(results.getDealerResults().get(GameResult.DRAW)).isEqualTo(1);
    }
}
