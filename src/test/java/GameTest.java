import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;
import org.junit.jupiter.api.Test;

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

}
