package domain;

import static domain.card.Rank.EIGHT;
import static domain.card.Rank.NINE;
import static domain.card.Rank.TEN;
import static domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.game.GameResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.Test;

class GameResultTest {
    @Test
    void 플레이어와_딜러의_최종_수익을_계산한다() {
        Players players = new Players(List.of("pobi", "jason"));
        Player firstPlayer = players.getPlayers().get(0);
        Player secondPlayer = players.getPlayers().get(1);
        Dealer dealer = createDealer(TEN, EIGHT);
        distributeCardsAndBetTo(firstPlayer, 10000, NINE, TEN);
        distributeCardsAndBetTo(secondPlayer, 20000, NINE, EIGHT);

        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.dealerProfit()).isEqualTo(10000);
    }

    private Player distributeCardsAndBetTo(Player player, int amount, Rank... ranks) {
        player.bet(amount);

        for (Rank rank : ranks) {
            player.receive(new Card(rank, HEART));
        }

        return player;
    }

    private Dealer createDealer(Rank... ranks) {
        Dealer dealer = new Dealer();
        for (Rank rank : ranks) {
            dealer.receive(new Card(rank, Suit.HEART));
        }
        return dealer;
    }
}
