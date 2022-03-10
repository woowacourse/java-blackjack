package blackjack.domain.gamer;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.result.BlackJackResult;
import blackjack.domain.result.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @Test
    @DisplayName("아무도 버스트가 아닐 때 플레이어 게임 결과 확인")
    void playerResultCreateNotBurst2() {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.THREE));
        dealer.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.NINE));
        dealer.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.EIGHT));

        Player pobi = new Player("pobi");
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.TWO));
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.EIGHT));
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.ACE));

        Player jason = new Player("jason");
        jason.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.SEVEN));
        jason.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.KING));

        BlackJackGame blackJackGame = new BlackJackGame(List.of("pobi", "json"));
        GameResult gameResult = blackJackGame.createResult(dealer, List.of(pobi, jason));

        Map<Name, BlackJackResult> playerResults = gameResult.getPlayerResult();

        BlackJackResult pobiResult = playerResults.get(new Name("pobi"));
        BlackJackResult jasonResult = playerResults.get(new Name("jason"));

        assertThat(pobiResult).isEqualTo(BlackJackResult.WIN);
        assertThat(jasonResult).isEqualTo(BlackJackResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 버스트일 때, 플레이어 게임 결과 확인")
    void playerResultCreateBurst() {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.THREE));
        dealer.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.NINE));
        dealer.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.TEN));
        dealer.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.ACE));

        Player pobi = new Player("pobi");
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.FOUR));
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.EIGHT));
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.TEN));

        Player jason = new Player("jason");
        jason.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.SEVEN));
        jason.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.KING));

        BlackJackGame blackJackGame = new BlackJackGame(List.of("pobi", "json"));
        GameResult gameResult = blackJackGame.createResult(dealer, List.of(pobi, jason));

        Map<Name, BlackJackResult> playerResults = gameResult.getPlayerResult();

        BlackJackResult pobiResult = playerResults.get(new Name("pobi"));
        BlackJackResult jasonResult = playerResults.get(new Name("jason"));

        assertThat(pobiResult).isEqualTo(BlackJackResult.LOSE);
        assertThat(jasonResult).isEqualTo(BlackJackResult.WIN);
    }



    @Test
    @DisplayName("딜러 게임 결과 확인")
    void dealerResultCreate() {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.THREE));
        dealer.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.NINE));
        dealer.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.EIGHT));

        Player pobi = new Player("pobi");
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.TWO));
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.EIGHT));
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.ACE));

        Player jason = new Player("jason");
        jason.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.SEVEN));
        jason.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.KING));

        BlackJackGame blackJackGame = new BlackJackGame(List.of("pobi", "json"));
        GameResult gameResult = blackJackGame.createResult(dealer, List.of(pobi, jason));

        Map<BlackJackResult, Integer> dealerResult = gameResult.getDealerResult();
        int winningCount = dealerResult.get(BlackJackResult.WIN);
        int losingCount = dealerResult.get(BlackJackResult.LOSE);
        int drawingCount = dealerResult.get(BlackJackResult.DRAW);

        assertThat(List.of(winningCount, losingCount, drawingCount))
                .containsExactly(1, 1, 0);
    }
}
