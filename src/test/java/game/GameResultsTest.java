package game;

import card.Card;
import card.CardNumber;
import card.Pattern;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameResultsTest {

    @Test
    void 플레이어들과_딜러_사이의_승패를_판정한다() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));
        Player player = new Player("a");
        player.draw(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.NINE)));

        GameResults gameResults = GameResults.of(dealer, List.of(player));

        Assertions.assertThat(gameResults.getGameResults()).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 플레이어들과_딜러_사이의_승패를_판정한다_둘다_버스트() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.TWO)));

        Player player = new Player("a");
        player.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.JACK),
                new Card(Pattern.HEART, CardNumber.TWO)));

        GameResults gameResults = GameResults.of(dealer, List.of(player));

        Assertions.assertThat(gameResults.getGameResults()).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 플레이어들과_딜러_사이의_승패를_판정한다_딜러만_버스트() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.TWO)));

        Player player = new Player("a");
        player.draw(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.TEN)));

        GameResults gameResults = GameResults.of(dealer, List.of(player));

        Assertions.assertThat(gameResults.getGameResults()).containsExactlyElementsOf(List.of(GameResult.WIN));
    }

    @Test
    void 플레이어들과_딜러_사이의_승패를_판정한다_둘다_블랙잭() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.ACE)));

        Player player = new Player("a");
        player.draw(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.ACE)));

        GameResults gameResults = GameResults.of(dealer, List.of(player));

        Assertions.assertThat(gameResults.getGameResults()).containsExactlyElementsOf(List.of(GameResult.DRAW));
    }

    @Test
    void 플레이어들과_딜러_사이의_승패를_판정한다_딜러만_블랙잭() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.ACE)));

        Player player = new Player("a");
        player.draw(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.TEN)));

        GameResults gameResults = GameResults.of(dealer, List.of(player));

        Assertions.assertThat(gameResults.getGameResults()).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 플레이어들과_딜러_사이의_승패를_판정한다_플레이어만_블랙잭() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));

        Player player = new Player("a");
        player.draw(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.ACE)));

        GameResults gameResults = GameResults.of(dealer, List.of(player));

        Assertions.assertThat(gameResults.getGameResults()).containsExactlyElementsOf(List.of(GameResult.BLACKJACK));
    }
}
