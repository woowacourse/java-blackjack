package domain.result;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCalculatorTest {

    private static List<Card> makeCardList(Card card1, Card card2) {
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        return cards;
    }

    private static List<Card> makeCardList(Card card1, Card card2, Card card3) {
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        return cards;
    }

    @Test
    @DisplayName("두명의 점수가 같은 경우 무승부 : 수익이 0으로 같음")
    void isSame() {
        Dealer dealer = new Dealer();
        Player player = new Player("오렌지",1000);
        List<Card> deckForTest = new ArrayList<>();
        ResultCalculator resultCalculator = new ResultCalculator();

        deckForTest.add(new Card(Symbol.ACE, Type.HEART));
        dealer.receiveCard(new Deck(deckForTest));

        deckForTest.add(new Card(Symbol.ACE, Type.HEART));
        player.receiveCard(new Deck(deckForTest));

        assertThat(resultCalculator.calculateResult(dealer, player)).isEqualTo(0);
        assertThat(resultCalculator.getDealerResult()).isEqualTo(0);
    }

    @Test
    @DisplayName("두명의 점수가 모두 21을 넘기는 경우 :  카드합이 21이 넘은 플레이어는 패,  21 이하인 플레이어는 승")
    void testWhenBothOverBlackJack() {
        ResultCalculator resultCalculator = new ResultCalculator();
        Dealer dealer = new Dealer();
        Player playerLose = new Player("오렌지",1000);
        Player playerWin = new Player("렌지",1000);

        List<Card> deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
                new Card(Symbol.JACK, Type.HEART), new Card(Symbol.KING, Type.HEART));
        dealer.receiveFirstCards(new Deck(deckForTest));
        dealer.receiveCard(new Deck(deckForTest));

        deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
                new Card(Symbol.JACK, Type.HEART), new Card(Symbol.NINE, Type.HEART));
        playerLose.receiveFirstCards(new Deck(deckForTest));
        playerLose.receiveCard(new Deck(deckForTest));

        deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.NINE, Type.HEART));
        playerWin.receiveFirstCards(new Deck(deckForTest));

        assertThat(resultCalculator.calculateResult(dealer, playerLose)).isEqualTo(-1000);
        assertThat(resultCalculator.calculateResult(dealer, playerWin)).isEqualTo(1000);
        assertThat(resultCalculator.getDealerResult()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어만 21을 넘기는 경우")
    void testWhenOnlyPlayerOverBlackJack() {
        ResultCalculator resultCalculator = new ResultCalculator();
        Dealer dealer = new Dealer();
        Player player = new Player("오렌지",1000);

        List<Card> deckForTest
                = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.JACK, Type.HEART));
        dealer.receiveFirstCards(new Deck(deckForTest));

        deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
                new Card(Symbol.JACK, Type.HEART), new Card(Symbol.NINE, Type.HEART));
        player.receiveFirstCards(new Deck(deckForTest));
        player.receiveCard(new Deck(deckForTest));

        assertThat(resultCalculator.calculateResult(dealer, player)).isEqualTo(-1000);
        assertThat(resultCalculator.getDealerResult()).isEqualTo(1000);
    }

    @Test
    @DisplayName("딜러만 21을 넘기는 경우")
    void testWhenOnlyDealerOverBlackJack() {
        ResultCalculator resultCalculator = new ResultCalculator();
        Dealer dealer = new Dealer();
        Player player = new Player("오렌지",1000);
        List<Card> deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
                new Card(Symbol.JACK, Type.HEART), new Card(Symbol.NINE, Type.HEART));

        dealer.receiveFirstCards(new Deck(deckForTest));
        dealer.receiveCard(new Deck(deckForTest));

        deckForTest
                = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.JACK, Type.HEART));
        player.receiveFirstCards(new Deck(deckForTest));

        assertThat(resultCalculator.calculateResult(dealer, player)).isEqualTo(1000);
        assertThat(resultCalculator.getDealerResult()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("딜러와 플레이어 둘 다 21이하인 경우")
    void testWhenBothLowerThanBlackJack() {
        ResultCalculator resultCalculator = new ResultCalculator();
        Dealer dealer = new Dealer();
        Player player = new Player("오렌지",1000);
        List<Card> deckForTest
                = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.NINE, Type.HEART));
        dealer.receiveFirstCards(new Deck(deckForTest));

        deckForTest
                = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.JACK, Type.HEART));
        player.receiveFirstCards(new Deck(deckForTest));

        assertThat(resultCalculator.calculateResult(dealer, player)).isEqualTo(1000);
        assertThat(resultCalculator.getDealerResult()).isEqualTo(-1000);
    }
}


