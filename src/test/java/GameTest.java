import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import blackjack.domain.ScoreCompareResult;
import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.service.CardDistributor;
import blackjack.service.Game;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Test;
import blackjack.service.RandomCardPicker;

public class GameTest {

    @Test
    void dealer_should_draw_card_until_score_at_least_17() {
        Dealer dealer = new Dealer();
        Random mockRandom = mock(Random.class);
        RandomCardPicker randomCardPicker = new RandomCardPicker(mockRandom);
        CardDistributor cardDistributor = new CardDistributor(randomCardPicker);

        // 2 하트, 2 스페이드, 2 클로버, 2 다이아몬드, 3 하트, 3 스페이드, 3 클로버, 3 다이아몬드, ...
        when(mockRandom.nextInt(52)).thenReturn(0);

        Game game = new Game(cardDistributor);
        game.dealerDrawsCardsUntilDone(dealer);

        assertThat(dealer.calculateTotalScore()).isEqualTo(17);
    }

    @Test
    void compare_score_when_player_win() {
        Player player = new Player("player1");
        Dealer dealer = new Dealer();

        player.receiveOneCard(new Card("A", "하트"));
        player.receiveOneCard(new Card("Q", "스페이드"));

        dealer.receiveOneCard(new Card("10", "하트"));
        dealer.receiveOneCard(new Card("7", "스페이드"));

        Game game = new Game(null);
        assertThat(game.compareScore(player, dealer)).isEqualTo(ScoreCompareResult.PLAYER_WIN);
    }

    @Test
    void compare_score_when_dealer_win() {
        Player player = new Player("player1");
        Dealer dealer = new Dealer();

        player.receiveOneCard(new Card("A", "하트"));
        player.receiveOneCard(new Card("4", "스페이드"));

        dealer.receiveOneCard(new Card("10", "하트"));
        dealer.receiveOneCard(new Card("9", "스페이드"));

        Game game = new Game(null);
        assertThat(game.compareScore(player, dealer)).isEqualTo(ScoreCompareResult.DEALER_WIN);
    }

    @Test
    void compare_score_when_push() {
        Player player = new Player("player1");
        Dealer dealer = new Dealer();

        player.receiveOneCard(new Card("A", "하트"));
        player.receiveOneCard(new Card("Q", "스페이드"));

        dealer.receiveOneCard(new Card("10", "하트"));
        dealer.receiveOneCard(new Card("A", "스페이드"));

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
            player.receiveOneCard(new Card(parts[0], parts[1]));
        }
        return player;
    }

    private Dealer createDealer(String... cards) {
        Dealer dealer = new Dealer();
        for (String card : cards) {
            String[] parts = card.split(":");
            dealer.receiveOneCard(new Card(parts[0], parts[1]));
        }
        return dealer;
    }
}
