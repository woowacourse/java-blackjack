package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {
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

    private static void makeDealerScoreNineteen(Dealer dealer) {
        List<Card> deckForTest =
            makeCardList(new Card(Symbol.NINE, Type.HEART), new Card(Symbol.QUEEN, Type.SPADE));
        dealer.receiveFirstCards(new Deck(deckForTest));
    }

    private static List<Card> makeCardList(Card card1, Card card2) {
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        return cards;
    }

    @Test
    @DisplayName("딜러의 수익 계산 테스트")
    void calculateResult() {
        Dealer dealer = new Dealer();
        Players players = PlayersFactory
            .create(Arrays.asList("오렌지", "히히"), Arrays.asList(5_000, 8_000));

        makePlayerScoreBlackJack(players.getPlayers().get(0));
        makePlayerScoreEighteen(players.getPlayers().get(1));
        makeDealerScoreNineteen(dealer);

        assertThat(players.calculateDealerRevenue(dealer)).isEqualTo((int) (-5_000 * 1.5 + 8_000));
    }
}
