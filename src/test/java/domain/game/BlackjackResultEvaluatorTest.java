package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.Pattern;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackResultEvaluatorTest {

    private static void provideWinCard(List<Card> cards) {
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.SEVEN));
    }

    private static void provideLoseCard(List<Card> cards) {
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.SIX));
    }

    private static void provideBurstCard(List<Card> cards) {
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        cards.add(new Card(Pattern.HEART, CardNumber.TEN));
    }

    private static void provideNonBurstCard(List<Card> cards) {
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.TWO));
        cards.add(new Card(Pattern.CLOVER, CardNumber.EIGHT));
    }

    private static void provideNonBlackJackCard(List<Card> cards) {
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.TWO));
        cards.add(new Card(Pattern.CLOVER, CardNumber.NINE));
    }

    private static void provideBlackJackCard(List<Card> cards) {
        cards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        cards.add(new Card(Pattern.CLOVER, CardNumber.ACE));
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_플레이어_일반_승리() {
        //given
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        provideLoseCard(dealerCards);

        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        provideWinCard(playerCards);

        //when
        List<GameResult> gameResult = blackjackResultEvaluator.judgeGameResult(List.of(player), dealer);

        //then
        assertThat(gameResult).containsExactly(GameResult.WIN);
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_플레이어_일반_21_승리() {
        //given
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        provideLoseCard(dealerCards);

        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        provideNonBlackJackCard(playerCards);

        //when
        List<GameResult> gameResult = blackjackResultEvaluator.judgeGameResult(List.of(player), dealer);

        //then
        assertThat(gameResult).containsExactly(GameResult.WIN);
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_플레이어_블랙잭_승리() {
        //given
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        provideLoseCard(dealerCards);

        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        provideBlackJackCard(playerCards);

        //when
        List<GameResult> gameResult = blackjackResultEvaluator.judgeGameResult(List.of(player), dealer);

        //then
        assertThat(gameResult).containsExactly(GameResult.BLACKJACK_WIN);
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_플레이어_패배() {
        //given
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        provideWinCard(dealerCards);

        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        provideLoseCard(playerCards);

        //when
        List<GameResult> gameResult = blackjackResultEvaluator.judgeGameResult(List.of(player), dealer);

        //then
        assertThat(gameResult).containsExactly(GameResult.LOSE);
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_무승부() {
        //given
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        provideNonBurstCard(dealerCards);

        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        provideNonBurstCard(playerCards);

        //when
        List<GameResult> gameResult = blackjackResultEvaluator.judgeGameResult(List.of(player), dealer);

        //then
        assertThat(gameResult).containsExactly(GameResult.DRAW);
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_버스트() {
        //given
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        provideBurstCard(dealerCards);

        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        provideBurstCard(playerCards);

        //when
        List<GameResult> gameResult = blackjackResultEvaluator.judgeGameResult(List.of(player), dealer);

        //then
        assertThat(gameResult).containsExactly(GameResult.LOSE);
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_버스트() {
        //given
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        provideBurstCard(dealerCards);

        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        provideNonBurstCard(playerCards);

        //when
        List<GameResult> gameResult = blackjackResultEvaluator.judgeGameResult(List.of(player), dealer);

        //then
        assertThat(gameResult).containsExactly(GameResult.WIN);
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_플레이어만_버스트() {
        //given
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        provideNonBurstCard(dealerCards);

        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        provideBurstCard(playerCards);

        //when
        List<GameResult> gameResult = blackjackResultEvaluator.judgeGameResult(List.of(player), dealer);

        //then
        assertThat(gameResult).containsExactly(GameResult.LOSE);
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_둘다_블랙잭() {
        //given
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        provideBlackJackCard(dealerCards);

        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        provideBlackJackCard(playerCards);

        //when
        List<GameResult> gameResult = blackjackResultEvaluator.judgeGameResult(List.of(player), dealer);

        //then
        assertThat(gameResult).containsExactly(GameResult.DRAW);
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러만_블랙잭() {
        //given
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();

        List<Card> dealerCards = dealer.getCards();
        provideBlackJackCard(dealerCards);

        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        provideNonBlackJackCard(playerCards);

        //when
        List<GameResult> gameResult = blackjackResultEvaluator.judgeGameResult(List.of(player), dealer);

        //then
        assertThat(gameResult).containsExactly(GameResult.LOSE);
    }

    @Test
    void 모든_플레이어들과_딜러_사이의_승패를_판정한다_딜러는_21_플레이어는_블랙잭() {
        //given_
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        provideNonBlackJackCard(dealerCards);

        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        provideBlackJackCard(playerCards);

        //when
        List<GameResult> gameResult = blackjackResultEvaluator.judgeGameResult(List.of(player), dealer);

        //then
        assertThat(gameResult).containsExactly(GameResult.BLACKJACK_WIN);
    }
}
