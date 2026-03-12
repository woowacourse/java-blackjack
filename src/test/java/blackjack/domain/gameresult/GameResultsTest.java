package blackjack.domain.gameresult;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.card.Card;
import blackjack.domain.card.Figure;
import blackjack.domain.card.Number;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameResultsTest {

    @Test
    @DisplayName("딜러가 19점, 플레이어는 블랙잭, 20, 17인 경우 딜러의 최종 수익을 계산한다.")
    void calculateGameResultsTest() {
        // given
        Dealer dealer = createDealerWithCards(
                new Card(Figure.HEART, Number.KING),
                new Card(Figure.HEART, Number.NINE));

        Player blackjackPlayer = createPlayerWithCards("p1", 10000,
                new Card(Figure.DIAMOND, Number.ACE),
                new Card(Figure.DIAMOND, Number.KING));
        Player winner = createPlayerWithCards("p2", 10000,
                new Card(Figure.HEART, Number.KING),
                new Card(Figure.HEART, Number.QUEEN));
        Player loser = createPlayerWithCards("p3", 10000,
                new Card(Figure.SPADE, Number.KING),
                new Card(Figure.SPADE, Number.SEVEN));
        Players players = new Players(List.of(blackjackPlayer, winner, loser));

        // when
        GameResults gameResults = GameResults.of(dealer, players);
        Map<Player, Integer> playersProfit = gameResults.getPlayersProfit();

        // then
        assertAll(
                () -> assertThat(playersProfit.get(blackjackPlayer)).isEqualTo(15000),
                () -> assertThat(playersProfit.get(winner)).isEqualTo(10000),
                () -> assertThat(playersProfit.get(loser)).isEqualTo(-10000),
                () -> assertThat(gameResults.getDealerProfit()).isEqualTo(-15000)
        );
    }

    private Dealer createDealerWithCards(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.receiveCard(card);
        }
        return dealer;
    }

    private Player createPlayerWithCards(String name, int amount, Card... cards) {
        Player player = new Player(name, new BettingAmount(amount));
        for (Card card : cards) {
            player.receiveCard(card);
        }
        return player;
    }

}
