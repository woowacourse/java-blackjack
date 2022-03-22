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

class BlackJackResultsTest {

    @Test
    @DisplayName("아무도 버스트가 아닐 때 플레이어 게임 결과 확인")
    void playerResultCreateNotBust2() {
        // given
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.NINE));
        List<Card> pobiCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        List<Card> jasonCards = List.of(new Card(CardShape.CLOVER, CardNumber.SEVEN), new Card(CardShape.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(dealerCards);
        Player pobi = new Player("pobi", pobiCards, 1000);
        Player jason = new Player("jason", jasonCards, 1000);
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));
        pobi.addCard(new Card(CardShape.CLOVER, CardNumber.ACE));
        BlackJackResults blackJackResults = new BlackJackResults(List.of(pobi, jason), dealer);

        // when
        Map<String, Integer> playerResults = blackJackResults.getPlayerResult();

        // then
        assertThat(playerResults.get("pobi")).isEqualTo(1000);
        assertThat(playerResults.get("jason")).isEqualTo(-1000);
    }

    @Test
    @DisplayName("딜러가 버스트이고 플레이어는 버스트가 아닌 경우 결과 확인")
    void playerResultCreateBust() {
        // given
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.TEN));
        List<Card> pobiCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        Dealer dealer = new Dealer(dealerCards);
        Player pobi = new Player("pobi", pobiCards, 1000);
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        pobi.addCard(new Card(CardShape.CLOVER, CardNumber.ACE));
        BlackJackResults blackJackResults = new BlackJackResults(List.of(pobi), dealer);

        // when
        Map<String, Integer> playerResults = blackJackResults.getPlayerResult();
        int pobiResult = playerResults.get("pobi");

        // then
        assertThat(pobiResult).isEqualTo(1000);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니지만 딜러가 진 경우")
    void dealerResultCreate() {
        // given
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.NINE));
        List<Card> jasonCards = List.of(new Card(CardShape.CLOVER, CardNumber.SEVEN), new Card(CardShape.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(dealerCards);
        Player jason = new Player("jason", jasonCards, 1000);

        // when
        BlackJackResults blackJackResults = new BlackJackResults(List.of(jason), dealer);
        int dealerResult = blackJackResults.getDealerResult();

        // then
        assertThat(dealerResult).isEqualTo(-1000);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니지만 플레이어가 진 경우")
    void playerResultCreate() {
        // given
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.FOUR), new Card(CardShape.CLOVER, CardNumber.NINE));
        List<Card> jasonCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(dealerCards);
        Player jason = new Player("jason", jasonCards, 1000);

        // when
        BlackJackResults blackJackResults = new BlackJackResults(List.of(jason), dealer);
        int dealerResult = blackJackResults.getPlayerResult().get("jason");

        // then
        assertThat(dealerResult).isEqualTo(-1000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아닌 21이지만, 딜러가 블랙잭일 경우 딜러의 승리")
    void blackJackDealerWin() {
        // given
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.ACE));
        List<Card> pobiCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        Dealer dealer = new Dealer(dealerCards);
        Player pobi = new Player("pobi", pobiCards, 1000);
        BlackJackResults blackJackResults = new BlackJackResults(List.of(pobi), dealer);

        // when
        int dealerResult = blackJackResults.getDealerResult();

        // then
        assertThat(dealerResult).isEqualTo(1000);
    }

    @Test
    @DisplayName("딜러가 블랙잭이 아닌 21이지만, 플레이어가 블랙잭일 경우 플레이어의 승리")
    void blackJackPlayerWin() {
        // given
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.TEN));
        List<Card> pobiCards = List.of(new Card(CardShape.CLOVER, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.ACE));
        Dealer dealer = new Dealer(dealerCards);
        Player pobi = new Player("pobi", pobiCards, 1000);
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.ACE));
        BlackJackResults blackJackResults = new BlackJackResults(List.of(pobi), dealer);
        int dealerResult = blackJackResults.getDealerResult();

        // when
        Map<String, Integer> playerResult = blackJackResults.getPlayerResult();

        // then
        assertThat(playerResult.get("pobi")).isEqualTo(1500);
        assertThat(dealerResult).isEqualTo(-1500);
    }

    @Test
    @DisplayName("플레이어, 딜러 모두 블랙잭이면 무승부")
    void blackJackDraw() {
        // given
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.ACE));
        List<Card> pobiCards = List.of(new Card(CardShape.CLOVER, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.ACE));
        Dealer dealer = new Dealer(dealerCards);
        Player pobi = new Player("pobi", pobiCards, 1000);

        // when
        BlackJackResults blackJackResults = new BlackJackResults(List.of(pobi), dealer);
        int dealerResult = blackJackResults.getDealerResult();

        // then
        assertThat(dealerResult).isEqualTo(0);
    }
}
