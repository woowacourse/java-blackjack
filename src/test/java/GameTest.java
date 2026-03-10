import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import blackjack.domain.*;
import blackjack.service.CardDistributor;
import blackjack.service.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Test;
import blackjack.service.RandomCardPicker;

public class GameTest {

    @Test
    void dealer_should_draw_card_until_score_at_least_17() {
        Dealer dealer = new Dealer();
        CardDistributor cardDistributor = new CardDistributor();
        Game game = new Game(cardDistributor);

        List<Card> cards = new ArrayList<>(List.of(
                new Card(Rank.EIGHT, Shape.HEART), // 8
                new Card(Rank.NINE, Shape.SPADE)   // 9
        ));

        CardPicker cardPicker = cards::removeFirst;
        game.dealerDrawsCardsUntilDone(dealer, cardPicker);
        assertThat(dealer.calculateTotalScore()).isEqualTo(17);
    }

    @Test
    void compare_score_when_player_win() {
        Player player = new Player("player1");
        Dealer dealer = new Dealer();

        player.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        player.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));

        dealer.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        dealer.receiveOneCard(new Card(Rank.SEVEN, Shape.SPADE));

        Game game = new Game(null);
        assertThat(game.compareScore(player, dealer)).isEqualTo(ScoreCompareResult.PLAYER_WIN);
    }

    @Test
    void compare_score_when_dealer_win() {
        Player player = new Player("player1");
        Dealer dealer = new Dealer();

        player.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        player.receiveOneCard(new Card(Rank.FOUR, Shape.SPADE));

        dealer.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        dealer.receiveOneCard(new Card(Rank.NINE, Shape.SPADE));

        Game game = new Game(null);
        assertThat(game.compareScore(player, dealer)).isEqualTo(ScoreCompareResult.DEALER_WIN);
    }

    @Test
    void compare_score_when_push() {
        Player player = new Player("player1");
        Dealer dealer = new Dealer();

        player.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        player.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));

        dealer.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        dealer.receiveOneCard(new Card(Rank.ACE, Shape.SPADE));

        Game game = new Game(null);
        assertThat(game.compareScore(player, dealer)).isEqualTo(ScoreCompareResult.PUSH);
    }

    @Test
    void judge_total_winner_result() {
        Player pobi = createPlayer("pobi", "2:하트", "8:스페이드", "A:클로버");
        Player jason = createPlayer("jason", "7:클로버", "K:스페이드");
        Player brown = createPlayer("brown", "10:하트", "10:클로버");
        Dealer dealer = createDealer("3:다이아몬드", "9:클로버", "8:다이아몬드");

        Game game = new Game(null);
        GameResult expected = new GameResult(
                Map.of(
                        ScoreCompareResult.DEALER_WIN, 1,
                        ScoreCompareResult.DEALER_LOSS, 1,
                        ScoreCompareResult.PUSH, 1),
                Map.of(
                        pobi, ScoreCompareResult.PLAYER_WIN,
                        jason, ScoreCompareResult.PLAYER_LOSS,
                        brown, ScoreCompareResult.PUSH)
        );

        GameResult actual = game.judgeTotalGameResult(List.of(pobi, jason, brown), dealer);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    private Player createPlayer(String name, String... cards) {
        Player player = new Player(name);
        for (String card : cards) {
            String[] parts = card.split(":");
            Rank rank = Rank.from(parts[0]);
            Shape shape = Shape.from(parts[1]);

            player.receiveOneCard(new Card(rank, shape));
        }
        return player;
    }

    private Dealer createDealer(String... cards) {
        Dealer dealer = new Dealer();
        for (String card : cards) {
            String[] parts = card.split(":");
            Rank rank = Rank.from(parts[0]);
            Shape shape = Shape.from(parts[1]);

            dealer.receiveOneCard(new Card(rank, shape));
        }
        return dealer;
    }
}
