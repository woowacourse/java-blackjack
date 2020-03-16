package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultCalculatorTest {

    private static List<Card> makeCardList(Card card1, Card card2) {
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        return cards;
    }

    @Test
    @DisplayName("두명의 점수가 같은 경우 무승부")
    void isSame() {
        Dealer dealer = new Dealer();
        Player player = new Player("오렌지");
        List<Card> deckForTest = new ArrayList<>();

        deckForTest.add(new Card(Symbol.ACE, Type.HEART));
        dealer.receiveCard(new Deck(deckForTest));

        deckForTest.add(new Card(Symbol.ACE, Type.HEART));
        player.receiveCard(new Deck(deckForTest));

        assertThat(ResultCalculator.calculatePlayerResult(dealer, player)).isEqualTo(Result.DRAW);
    }

    private static List<Card> makeCardList(Card card1, Card card2, Card card3) {
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        return cards;
    }

    @Test
    @DisplayName("딜러의 승패 결과 확인 테스트")
    void calculateDealerResult() {
        Map<Result, Integer> expected = new HashMap<>();
        expected.put(Result.WIN, 0);
        expected.put(Result.DRAW, 1);
        expected.put(Result.LOSE, 1);

        Dealer dealer = new Dealer();
        Players players = new Players(Arrays.asList("오렌지", "히히"));
        Player winPlayer = players.getPlayers().get(0);
        Player drawPlayer = players.getPlayers().get(1);
        List<Card> deckForTest =
            makeCardList(new Card(Symbol.ACE, Type.HEART), new Card(Symbol.QUEEN, Type.HEART));
        winPlayer.receiveFirstCards(new Deck(deckForTest));

        deckForTest =
            makeCardList(new Card(Symbol.EIGHT, Type.SPADE), new Card(Symbol.QUEEN, Type.SPADE));
        drawPlayer.receiveFirstCards(new Deck(deckForTest));

        deckForTest =
            makeCardList(new Card(Symbol.EIGHT, Type.SPADE), new Card(Symbol.QUEEN, Type.SPADE));
        dealer.receiveFirstCards(new Deck(deckForTest));

        assertThat(
            ResultCalculator.calculateDealerResult(dealer, players)
        ).isEqualTo(new DealerResult(expected));
    }

    @Test
    @DisplayName("두명의 점수가 모두 21을 넘기는 경우")
    void testWhenBothOverBlackJack() {
        Dealer dealer = new Dealer();
        Player player = new Player("오렌지");

        List<Card> deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
            new Card(Symbol.JACK, Type.HEART), new Card(Symbol.KING, Type.HEART));
        dealer.receiveFirstCards(new Deck(deckForTest));
        dealer.receiveCard(new Deck(deckForTest));

        deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
            new Card(Symbol.JACK, Type.HEART), new Card(Symbol.NINE, Type.HEART));
        player.receiveFirstCards(new Deck(deckForTest));
        player.receiveCard(new Deck(deckForTest));

        assertThat(ResultCalculator.calculatePlayerResult(dealer, player)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("플레이어만 21을 넘기는 경우")
    void testWhenOnlyPlayerOverBlackJack() {
        Dealer dealer = new Dealer();
        Player player = new Player("오렌지");

        List<Card> deckForTest
            = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.JACK, Type.HEART));
        dealer.receiveFirstCards(new Deck(deckForTest));

        deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
            new Card(Symbol.JACK, Type.HEART), new Card(Symbol.NINE, Type.HEART));
        player.receiveFirstCards(new Deck(deckForTest));
        player.receiveCard(new Deck(deckForTest));

        assertThat(ResultCalculator.calculatePlayerResult(dealer, player)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러만 21을 넘기는 경우")
    void testWhenOnlyDealerOverBlackJack() {
        Dealer dealer = new Dealer();
        Player player = new Player("오렌지");
        List<Card> deckForTest = makeCardList(new Card(Symbol.QUEEN, Type.HEART),
            new Card(Symbol.JACK, Type.HEART), new Card(Symbol.NINE, Type.HEART));

        dealer.receiveFirstCards(new Deck(deckForTest));
        dealer.receiveCard(new Deck(deckForTest));

        deckForTest
            = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.JACK, Type.HEART));
        player.receiveFirstCards(new Deck(deckForTest));

        assertThat(ResultCalculator.calculatePlayerResult(dealer, player)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어 둘 다 21이하인 경우")
    void testWhenBothLowerThanBlackJack() {
        Dealer dealer = new Dealer();
        Player player = new Player("오렌지");
        List<Card> deckForTest
            = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.NINE, Type.HEART));
        dealer.receiveFirstCards(new Deck(deckForTest));

        deckForTest
            = makeCardList(new Card(Symbol.QUEEN, Type.HEART), new Card(Symbol.JACK, Type.HEART));
        player.receiveFirstCards(new Deck(deckForTest));

        assertThat(ResultCalculator.calculatePlayerResult(dealer, player)).isEqualTo(Result.WIN);
    }
}


