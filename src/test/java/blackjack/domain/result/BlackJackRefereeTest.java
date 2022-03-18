package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackRefereeTest {

    @Test
    @DisplayName("아무도 버스트가 아닐 때 플레이어 게임 결과 확인")
    void playerResultCreateNotBurst2() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.NINE));
        List<Card> pobiCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        List<Card> jasonCards = List.of(new Card(CardShape.CLOVER, CardNumber.SEVEN), new Card(CardShape.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(dealerCards);
        Player pobi = new Player("pobi", pobiCards, 1000);
        Player jason = new Player("jason", jasonCards, 1000);
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));
        pobi.addCard(new Card(CardShape.CLOVER, CardNumber.ACE));
        BlackJackReferee blackJackReferee = new BlackJackReferee(List.of(pobi, jason), dealer);

        Map<String, Integer> playerResults = blackJackReferee.getPlayerResult();

        assertThat(playerResults.get("pobi")).isEqualTo(1000);
        assertThat(playerResults.get("jason")).isEqualTo(-1000);
    }

    @Test
    @DisplayName("딜러가 버스트일 때, 플레이어 게임 결과 확인")
    void playerResultCreateBurst() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.TEN));
        List<Card> pobiCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        Dealer dealer = new Dealer(dealerCards);
        Player pobi = new Player("pobi", pobiCards, 1000);

        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        pobi.addCard(new Card(CardShape.CLOVER, CardNumber.ACE));

        BlackJackReferee blackJackReferee = new BlackJackReferee(List.of(pobi), dealer);

        Map<String, Integer> playerResults = blackJackReferee.getPlayerResult();

        int pobiResult = playerResults.get("pobi");
        assertThat(pobiResult).isEqualTo(1000);
    }

    @Test
    @DisplayName("딜러 게임 결과 확인")
    void dealerResultCreate() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.NINE));
        List<Card> pobiCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        List<Card> jasonCards = List.of(new Card(CardShape.CLOVER, CardNumber.SEVEN), new Card(CardShape.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(dealerCards);
        Player pobi = new Player("pobi", pobiCards, 1000);
        Player jason = new Player("jason", jasonCards, 1000);

        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));
        pobi.addCard(new Card(CardShape.CLOVER, CardNumber.ACE));

        BlackJackReferee blackJackReferee = new BlackJackReferee(List.of(pobi, jason), dealer);

        int dealerResult = blackJackReferee.getDealerResult();

        assertThat(dealerResult).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아닌 21이지만, 딜러가 블랙잭일 경우 딜러의 승리")
    void blackJackDealerWin() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.ACE));
        List<Card> pobiCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        Dealer dealer = new Dealer(dealerCards);
        Player pobi = new Player("pobi", pobiCards, 1000);
        BlackJackReferee blackJackReferee = new BlackJackReferee(List.of(pobi), dealer);

        int dealerResult = blackJackReferee.getDealerResult();

        assertThat(dealerResult).isEqualTo(1000);
    }

    @Test
    @DisplayName("딜러가 블랙잭이 아닌 21이지만, 플레이어가 블랙잭일 경우 딜러의 승리")
    void blackJackPlayerWin() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.TEN));
        List<Card> pobiCards = List.of(new Card(CardShape.CLOVER, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.ACE));
        Dealer dealer = new Dealer(dealerCards);
        Player pobi = new Player("pobi", pobiCards, 1000);
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.ACE));
        BlackJackReferee blackJackReferee = new BlackJackReferee(List.of(pobi), dealer);
        int dealerResult = blackJackReferee.getDealerResult();

        Map<String, Integer> playerResult = blackJackReferee.getPlayerResult();

        assertThat(playerResult.get("pobi")).isEqualTo(1500);
        assertThat(dealerResult).isEqualTo(-1500);
    }

    @Test
    @DisplayName("플레이어, 딜러 모두 블랙잭이면 무승부이다.")
    void blackJackDraw() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.ACE));
        List<Card> pobiCards = List.of(new Card(CardShape.CLOVER, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.ACE));
        Dealer dealer = new Dealer(dealerCards);
        Player pobi = new Player("pobi", pobiCards, 1000);
        BlackJackReferee blackJackReferee = new BlackJackReferee(List.of(pobi), dealer);

        int dealerResult = blackJackReferee.getDealerResult();

        assertThat(dealerResult).isEqualTo(0);
    }
}
