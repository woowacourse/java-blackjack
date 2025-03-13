package game;

import card.Card;
import card.CardNumber;
import card.Pattern;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다() {
        //given
        Dealer dealer = new Dealer();
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));

        Player player = new Player("pobi");
        player.drawCard(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.NINE)));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_둘다_버스트() {
        //given
        Dealer dealer = new Dealer();
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.TEN)));

        Player player = new Player("pobi");
        player.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.NINE),
                new Card(Pattern.HEART, CardNumber.NINE),
                new Card(Pattern.CLOVER, CardNumber.NINE)));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러만_버스트() {
        //given
        Dealer dealer = new Dealer();
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.TEN)));

        Player player = new Player("pobi");
        player.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.NINE),
                new Card(Pattern.HEART, CardNumber.NINE)));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러의_총합이_21에_가깝다() {
        //given
        Dealer dealer = new Dealer();
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));

        Player player = new Player("pobi");
        player.drawCard(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.NINE)));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_플레이어의_총합이_21에_가깝다() {
        //given
        Dealer dealer = new Dealer();
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.NINE)));

        Player player = new Player("pobi");
        player.drawCard(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.TEN)));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러와_플레이어의_총합이_같다() {
        //given
        Dealer dealer = new Dealer();
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));

        Player player = new Player("pobi");
        player.drawCard(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.TEN)));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_둘다_블랙잭() {
        //given
        Dealer dealer = new Dealer();
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.ACE)));

        Player player = new Player("pobi");
        player.drawCard(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.ACE)));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_딜러만_블랙잭() {
        //given
        Dealer dealer = new Dealer();
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.ACE)));

        Player player = new Player("pobi");
        player.drawCard(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.TEN)));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어와_딜러_사이의_승패를_판정한다_플레이어만_블랙잭() {
        //given
        Dealer dealer = new Dealer();
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));

        Player player = new Player("pobi");
        player.drawCard(List.of(
                new Card(Pattern.DIAMOND, CardNumber.TEN),
                new Card(Pattern.HEART, CardNumber.ACE)));

        //when
        GameResult gameResult = GameResult.of(dealer, player);

        //then
        Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    void 딜러의_승리_횟수를_계산한다() {
        //when
        int winCount = GameResult.WIN.countReversedGameResult(
                List.of(GameResult.WIN, GameResult.WIN, GameResult.LOSE, GameResult.DRAW));

        //then
        Assertions.assertThat(winCount).isEqualTo(1);

    }

    @Test
    void 딜러의_패배_횟수를_계산한다() {
        //when
        int winCount = GameResult.LOSE.countReversedGameResult(
                List.of(GameResult.WIN, GameResult.WIN, GameResult.LOSE, GameResult.DRAW));

        //then
        Assertions.assertThat(winCount).isEqualTo(2);

    }

    @Test
    void 딜러의_무승부_횟수를_계산한다() {
        //when
        int winCount = GameResult.DRAW.countReversedGameResult(
                List.of(GameResult.WIN, GameResult.WIN, GameResult.LOSE, GameResult.DRAW));

        //then
        Assertions.assertThat(winCount).isEqualTo(1);
    }
}
