package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.Pattern;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다() {
        //given
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.SEVEN));

        Player player = new Player("pobi");
        List<Card> playerCards = player.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.SIX));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_버스트() {
        //given
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        Player player = new Player("pobi");
        List<Card> playerCards = player.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        playerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_버스트() {
        //given
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.HEART, CardNumber.TEN));

        Player player = new Player("pobi");
        List<Card> playerCards = player.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.WIN));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_블랙잭() {
        //given
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi");
        List<Card> playerCards = player.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.DRAW));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_블랙잭() {
        //given
        Dealer dealer = new Dealer();

        List<Card> dealerCards = dealer.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi");
        List<Card> playerCards = player.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.EIGHT));
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.LOSE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_플레이어만_블랙잭() {
        //given
        Dealer dealer = new Dealer();

        List<Card> dealerCards = dealer.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.EIGHT));
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        dealerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        Player player = new Player("pobi");
        List<Card> playerCards = player.getCards();
        playerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        playerCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when
        List<GameResult> gameResult = dealer.judgeGameResult(List.of(player));

        //then
        Assertions.assertThat(gameResult).containsExactlyElementsOf(List.of(GameResult.WIN));
    }
}
