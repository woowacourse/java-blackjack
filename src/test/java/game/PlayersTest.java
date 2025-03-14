package game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import card.Pattern;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    void 플레이어를_정상적으로_생성한다() {
        assertThatCode(() -> new Players(List.of(
                new Player("pobi"),
                new Player("font")
        ))).doesNotThrowAnyException();
    }

    @Test
    void 인원_수가_5명_초과이면_예외를_던진다() {
        //given
        List<Player> players = List.of(
                new Player("a"),
                new Player("b"),
                new Player("c"),
                new Player("d"),
                new Player("e"),
                new Player("f"));

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 인원_수가_0명이면_예외를_던진다() {
        //given
        List<Player> players = List.of();

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은_이름의_플레이어가_여러_명_있으면_예외를_던진다() {
        //given
        List<Player> players = List.of(
                new Player("a"),
                new Player("a"));

        //when & then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 모든_플레이어는_게임_시작_시_카드를_드로우한다() {
        //given
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);
        Players players = new Players(List.of(
                new Player("a"),
                new Player("b")));

        //when
        players.draw(cardDeck);

        //when
        Assertions.assertThat(players.getPlayers().get(0).getCardsCount()).isEqualTo(2);
        Assertions.assertThat(players.getPlayers().get(1).getCardsCount()).isEqualTo(2);
    }

    @Test
    void 모든_플레이어의_이름을_반환한다() {
        //given
        Players players = new Players(List.of(
                new Player("aa"),
                new Player("bb")));

        //when
        List<String> allPlayerNames = players.getAllPlayerNames();

        //then
        Assertions.assertThat(allPlayerNames).containsExactly("aa", "bb");
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다() {
        //given
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));

        Player player = new Player("a");
        Players players = new Players(List.of(player));
        player.draw(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.NINE)));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_버스트() {
        //given
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.TWO)));

        Player player = new Player("a");
        Players players = new Players(List.of(player));
        player.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.KING),
                new Card(Pattern.SPADE, CardNumber.JACK),
                new Card(Pattern.HEART, CardNumber.TWO)));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_버스트() {
        //given
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.TWO)));

        Player player = new Player("a");
        Players players = new Players(List.of(player));
        player.draw(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.TEN)));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.WIN));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_블랙잭() {
        //given
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.ACE)));

        Player player = new Player("a");
        Players players = new Players(List.of(player));
        player.draw(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.ACE)));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.DRAW));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_블랙잭() {
        //given
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.ACE)));

        Player player = new Player("a");
        Players players = new Players(List.of(player));
        player.draw(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.TEN)));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_플레이어만_블랙잭() {
        //given
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));

        Player player = new Player("a");
        Players players = new Players(List.of(player));
        player.draw(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.ACE)));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.BLACKJACK));
    }

    @Test
    void 모든_플레이어의_수익을_계산한다() {
        //given
        Players players = new Players(List.of(
                new Player("a", 1000),
                new Player("b", 1000),
                new Player("c", 1000),
                new Player("d", 1000)));
        List<GameResult> gameResults = List.of(
                GameResult.BLACKJACK, GameResult.WIN, GameResult.DRAW, GameResult.LOSE);

        //when
        List<Integer> profits = players.evaluate(gameResults);

        //then
        assertThat(profits).containsExactlyElementsOf(List.of(1500, 1000, 0, -1000));
    }
}
