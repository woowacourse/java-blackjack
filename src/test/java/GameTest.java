import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import blackjack.domain.Rank;
import blackjack.domain.ScoreCompareResult;
import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.Shape;
import blackjack.service.CardDistributor;
import blackjack.service.Game;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Test;
import blackjack.service.RandomCardPicker;

public class GameTest {

    @Test
    void dealer_should_draw_card_until_score_at_least_17() { // TODO: 책임 CardDistributor로 이동
        Dealer dealer = new Dealer();
        Random mockRandom = mock(Random.class);
        RandomCardPicker randomCardPicker = new RandomCardPicker(mockRandom);
        CardDistributor cardDistributor = new CardDistributor(randomCardPicker);

        // 2 하트, 2 스페이드, 2 클로버, 2 다이아몬드, 3 하트, 3 스페이드, 3 클로버, 3 다이아몬드, ...
        when(mockRandom.nextInt(52)).thenReturn(0);

        cardDistributor.distributeCardsToDealerUntilScoreAtLeast(dealer);

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

        assertThat(Game.compareScore(player, dealer)).isEqualTo(ScoreCompareResult.PLAYER_WIN);
    }

    @Test
    void compare_score_when_dealer_win() {
        Player player = new Player("player1");
        Dealer dealer = new Dealer();

        player.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        player.receiveOneCard(new Card(Rank.FOUR, Shape.SPADE));

        dealer.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        dealer.receiveOneCard(new Card(Rank.NINE, Shape.SPADE));

        assertThat(Game.compareScore(player, dealer)).isEqualTo(ScoreCompareResult.DEALER_WIN);
    }

    @Test
    void compare_score_when_push() {
        Player player = new Player("player1");
        Dealer dealer = new Dealer();

        player.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        player.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));

        dealer.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        dealer.receiveOneCard(new Card(Rank.ACE, Shape.SPADE));

        assertThat(Game.compareScore(player, dealer)).isEqualTo(ScoreCompareResult.PUSH);
    }

    @Test
    void judge_total_winner_result() {
        Player pobi = createPlayer("pobi",
                new Card(Rank.TWO, Shape.HEART), new Card(Rank.EIGHT, Shape.SPADE), new Card(Rank.ACE, Shape.CLUB));
        Player jason = createPlayer("jason",
                new Card(Rank.SEVEN, Shape.CLUB), new Card(Rank.KING, Shape.SPADE));
        Player brown = createPlayer("brown",
                new Card(Rank.TEN, Shape.HEART), new Card(Rank.TEN, Shape.CLUB));
        Dealer dealer = createDealer(
                new Card(Rank.THREE, Shape.DIAMOND), new Card(Rank.NINE, Shape.CLUB),
                new Card(Rank.EIGHT, Shape.DIAMOND));

        Game game = new Game(List.of(pobi, jason, brown), dealer);

        Map<ScoreCompareResult, Integer> dealerResult = Map.of(
                ScoreCompareResult.DEALER_WIN, 1,
                ScoreCompareResult.DEALER_LOSE, 1,
                ScoreCompareResult.PUSH, 1
        );

        LinkedHashMap<Player, ScoreCompareResult> playerResults = new LinkedHashMap<>();
        playerResults.put(pobi, ScoreCompareResult.PLAYER_WIN);
        playerResults.put(jason, ScoreCompareResult.PLAYER_LOSE);
        playerResults.put(brown, ScoreCompareResult.PUSH);

        GameResult expected = new GameResult(
                dealerResult,
                playerResults
        );

        GameResult actual = game.judgeTotalGameResult();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    private Player createPlayer(String name, Card... cards) {
        Player player = new Player(name);
        for (Card card : cards) {
            player.receiveOneCard(card);
        }
        return player;
    }

    private Dealer createDealer(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.receiveOneCard(card);
        }
        return dealer;
    }
}
