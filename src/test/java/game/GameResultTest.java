package game;

import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardNumber;
import card.Pattern;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GameResultTest {

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));
        Player player = new Player("pobi");
        player.draw(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.NINE)));

        GameResult gameResult = GameResult.determine(dealer, player);

        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_둘다_버스트() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.TEN)));
        Player player = new Player("pobi");
        player.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.NINE),
                new Card(Pattern.HEART, CardNumber.NINE),
                new Card(Pattern.CLOVER, CardNumber.NINE)));

        GameResult gameResult = GameResult.determine(dealer, player);

        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러만_버스트() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.TEN)));
        Player player = new Player("pobi");
        player.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.NINE),
                new Card(Pattern.HEART, CardNumber.NINE)));

        GameResult gameResult = GameResult.determine(dealer, player);

        Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러의_총합이_21에_가깝다() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));
        Player player = new Player("pobi");
        player.draw(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.NINE)));

        GameResult gameResult = GameResult.determine(dealer, player);

        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_플레이어의_총합이_21에_가깝다() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.NINE)));
        Player player = new Player("pobi");
        player.draw(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.TEN)));

        GameResult gameResult = GameResult.determine(dealer, player);

        Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러와_플레이어의_총합이_같다() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));
        Player player = new Player("pobi");
        player.draw(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.TEN)));

        GameResult gameResult = GameResult.determine(dealer, player);

        Assertions.assertThat(gameResult).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_둘다_블랙잭() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.ACE)));
        Player player = new Player("pobi");
        player.draw(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.ACE)));

        GameResult gameResult = GameResult.determine(dealer, player);

        Assertions.assertThat(gameResult).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러만_블랙잭() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.ACE)));
        Player player = new Player("pobi");
        player.draw(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.TEN)));

        GameResult gameResult = GameResult.determine(dealer, player);

        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_플레이어만_블랙잭() {
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));
        Player player = new Player("pobi");
        player.draw(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.ACE)));

        GameResult gameResult = GameResult.determine(dealer, player);

        Assertions.assertThat(gameResult).isEqualTo(GameResult.BLACKJACK);
    }

    @ParameterizedTest
    @MethodSource(value = "provideBetAndProfit")
    void 승패_결과에_따른_수익을_반환한다(GameResult result, int expected) {
        Betting betting = new Betting(1000);

        int profit = result.evaluate(betting.getBetting());

        assertThat(profit).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBetAndProfit() {
        return Stream.of(
                Arguments.of(GameResult.WIN, 1000),
                Arguments.of(GameResult.DRAW, 0),
                Arguments.of(GameResult.LOSE, -1000),
                Arguments.of(GameResult.BLACKJACK, 1500)
        );
    }

}
