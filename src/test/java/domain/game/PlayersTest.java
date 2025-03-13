package domain.game;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.Pattern;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    void 플레이어를_정상적으로_생성한다() {
        //given
        List<String> playerNames = List.of("aa", "bb", "cc", "dd", "ee");

        //when & then
        assertThatCode(() -> new Players(playerNames))
                .doesNotThrowAnyException();
    }

    @Test
    void 인원_수가_5명_초과이면_예외를_던진다() {
        //given
        List<String> playerNames = List.of("aa", "bb", "cc", "dd", "ee", "ff");

        //when & then
        assertThatThrownBy(
                () -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 인원_수가_0명이면_예외를_던진다() {
        //given
        List<String> playerNames = List.of();

        //when & then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은_이름의_플레이어가_여러_명_있으면_예외를_던진다() {
        //given
        List<String> playerNames = List.of("aa", "aa");

        //when & then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 모든_플레이어는_게임_시작_시_카드를_드로우한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards(ArrayList::new);
        List<String> playerNames = List.of("aa", "bb");
        Players players = new Players(playerNames);

        //when
        players.drawCard(cardDeck);

        //when
        Assertions.assertThat(players.getPlayers().get(0).getCardsCount()).isEqualTo(2);
        Assertions.assertThat(players.getPlayers().get(1).getCardsCount()).isEqualTo(2);
    }

    @Test
    void 모든_플레이어의_이름을_반환한다() {
        //given
        List<String> playerNames = List.of("aa", "bb");
        Players players = new Players(playerNames);

        //when
        List<String> allPlayerNames = players.getAllPlayerNames();

        //then
        Assertions.assertThat(allPlayerNames).containsExactly("aa", "bb");
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다() {
        //given
        Dealer dealer = new Dealer();
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));

        Players players = new Players(List.of("pobi"));
        players.getPlayers().getFirst().drawCard(List.of(
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
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.TWO)));

        Players players = new Players(List.of("pobi"));
        players.getPlayers().getFirst().drawCard(List.of(
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
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.TWO)));

        Players players = new Players(List.of("pobi"));
        players.getPlayers().getFirst().drawCard(List.of(
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
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.ACE)));

        Players players = new Players(List.of("pobi"));
        players.getPlayers().getFirst().drawCard(List.of(
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
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.ACE)));

        Players players = new Players(List.of("pobi"));
        players.getPlayers().getFirst().drawCard(List.of(
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
        dealer.drawCard(List.of(
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.CLOVER, CardNumber.TEN)));

        Players players = new Players(List.of("pobi"));
        players.getPlayers().getFirst().drawCard(List.of(
                new Card(Pattern.HEART, CardNumber.TEN),
                new Card(Pattern.DIAMOND, CardNumber.ACE)));

        //when
        List<GameResult> gameResult = players.judgeGameResult(dealer);

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.WIN));
    }
}
