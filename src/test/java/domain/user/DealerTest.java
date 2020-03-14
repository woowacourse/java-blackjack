package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Cards;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.DealerResult;
import domain.result.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("초기 2장의 카드 받기 테스트")
    void receiveFirstCards() {
        Deck deck = CardFactory.createDeck();
        Dealer dealer = new Dealer();
        dealer.receiveFirstCards(deck);
        assertThat(dealer.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("입력된 스코어가 딜러의 스코어와 같은지 테스트")
    void isScoreSame() {
        Dealer dealer = new Dealer();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Symbol.THREE, Type.SPADE));
        cards.add(new Card(Symbol.TWO, Type.SPADE));

        dealer.receiveCard(new Deck(cards));
        dealer.receiveCard(new Deck(cards));

        assertThat(dealer.isScoreSame(5)).isTrue();
    }

    @Test
    @DisplayName("카드를 더 받을수 없는 상태인지 잘 파악하는지 테스트")
    void cannotReceiveCard() {
        Deck deck = CardFactory.createDeck();
        Dealer dealer = new Dealer();
        dealer.receiveFirstCards(deck);
        while (!dealer.isLargerThan(Cards.MAX_SUM_FOR_DEALER_MORE_CARD)) {
            dealer.receiveCard(deck);
        }
        assertThat(dealer.canReceiveCard()).isFalse();
    }

    @Test
    @DisplayName("카드를 더 받을수 있는 상태인지를 잘 파악하는지 테스트")
    void canReceiveCard() {
        Deck deck = CardFactory.createDeck();
        Dealer dealer = new Dealer();
        dealer.receiveFirstCards(deck);
        assertThat(dealer.canReceiveCard()).isTrue();
    }

    private static Map<Result, Integer> makeDealerResultMap(int winCount, int drawCount,
        int loseCount) {
        Map<Result, Integer> dealerResult = new HashMap<>();
        dealerResult.put(Result.WIN, winCount);
        dealerResult.put(Result.DRAW, drawCount);
        dealerResult.put(Result.LOSE, loseCount);
        return dealerResult;
    }

    private static void makePlayerScoreBlackJack(Player player) {
        List<Card> deckForTest =
            makeCardList(new Card(Symbol.ACE, Type.HEART), new Card(Symbol.QUEEN, Type.HEART));
        player.receiveFirstCards(new Deck(deckForTest));
    }

    private static void makePlayerScoreEighteen(Player player) {
        List<Card> deckForTest =
            makeCardList(new Card(Symbol.EIGHT, Type.HEART), new Card(Symbol.QUEEN, Type.SPADE));
        player.receiveFirstCards(new Deck(deckForTest));
    }

    private static void makeDealerScoreEighteen(Dealer dealer) {
        List<Card> deckForTest =
            makeCardList(new Card(Symbol.EIGHT, Type.HEART), new Card(Symbol.QUEEN, Type.SPADE));
        dealer.receiveFirstCards(new Deck(deckForTest));
    }

    private static List<Card> makeCardList(Card card1, Card card2) {
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        return cards;
    }

    @Test
    @DisplayName("딜러의 승패 결과 확인 테스트")
    void calculateResult() {
        Map<Result, Integer> expected = makeDealerResultMap(0, 1, 1);

        Dealer dealer = new Dealer();
        Players players = new Players(Arrays.asList("오렌지", "히히"));

        makePlayerScoreBlackJack(players.getPlayers().get(0));    // 딜러를 이김
        makePlayerScoreEighteen(players.getPlayers().get(1));     // 딜러와 비김
        makeDealerScoreEighteen(dealer);

        assertThat(dealer.calculateResult(players)).isEqualTo(new DealerResult(expected));
    }
}
