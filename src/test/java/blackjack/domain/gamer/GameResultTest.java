package blackjack.domain.gamer;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.result.BlackJackResult;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.PlayerResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class GameResultTest {

    @Test
    @DisplayName("아무도 버스트가 아닐 때 플레이어 게임 결과 확인")
    void playerResultCreateNotBurst() {
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

        BlackJackGame blackJackGame = new BlackJackGame();
        GameResult gameResult = blackJackGame.createResult(dealer, List.of(pobi, jason));

        List<PlayerResult> playerResults = gameResult.getPlayerResult();

        assertThat(playerResults)
                .extracting("name", "result")
                .contains(tuple(new Name("pobi"), BlackJackResult.WIN),
                        tuple(new Name("jason"), BlackJackResult.LOSE));
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

        BlackJackGame blackJackGame = new BlackJackGame();
        GameResult gameResult = blackJackGame.createResult(dealer, List.of(pobi, jason));

        List<PlayerResult> playerResults = gameResult.getPlayerResult();

        assertThat(playerResults)
                .extracting("name", "result")
                .contains(tuple(new Name("pobi"), BlackJackResult.LOSE),
                        tuple(new Name("jason"), BlackJackResult.WIN));
    }
}
