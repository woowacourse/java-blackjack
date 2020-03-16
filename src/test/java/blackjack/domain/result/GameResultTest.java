package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    private Dealer dealer;
    private Players players;
    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        Deck deckForDealer = createDeckForDealer();
        drawDealerCards(deckForDealer);

        Deck deckForPlayers = createDeckForPlayers();
        drawPlayersCards(deckForPlayers);

        gameResult = GameResult.of(dealer, players);
    }

    private Deck createDeckForDealer() {
        Stack<Card> cardsForDealer = new Stack<>();

        cardsForDealer.push(new Card(Symbol.QUEEN, Type.SPADE));
        cardsForDealer.push(new Card(Symbol.KING, Type.SPADE));

        return new Deck(cardsForDealer);
    }

    private void drawDealerCards(Deck deckForDealer) {
        dealer = Dealer.create();
        dealer.drawCard(deckForDealer);
        dealer.drawCard(deckForDealer);
    }

    private Deck createDeckForPlayers() {
        Stack<Card> cardsForPlayers = new Stack<>();

        List<Card> cardsValue = Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE),
                new Card(Symbol.ACE, Type.SPADE),
                new Card(Symbol.QUEEN, Type.SPADE),
                new Card(Symbol.KING, Type.SPADE),
                new Card(Symbol.KING, Type.SPADE),
                new Card(Symbol.NINE, Type.SPADE));

        cardsForPlayers.addAll(cardsValue);

        return new Deck(cardsForPlayers);
    }

    private void drawPlayersCards(Deck deckForPlayers) {
        players = Players.of("그니, 무늬, 포비");
        for (Player player : players.getPlayers()) {
            player.drawCard(deckForPlayers);
            player.drawCard(deckForPlayers);
        }
    }

    @Test
    void of() {
        assertThat(GameResult.of(dealer, players)).isNotNull();
    }

    @Test
    void getDealerWinCount() {
        assertThat(gameResult.getDealerWinCount()).isEqualTo(1);
    }

    @Test
    void getDealerDrawCount() {
        assertThat(gameResult.getDealerDrawCount()).isEqualTo(1);
    }

    @Test
    void getDealerLoseCount() {
        assertThat(gameResult.getDealerLoseCount()).isEqualTo(1);
    }

    @Test
    void getPlayerResults() {
        Map<Player, ResultType> expected = new HashMap<>();
        List<Player> playersValue = players.getPlayers();

        expected.put(playersValue.get(0), ResultType.LOSE);
        expected.put(playersValue.get(1), ResultType.DRAW);
        expected.put(playersValue.get(2), ResultType.WIN);

        assertThat(gameResult.getPlayerResults()).isEqualTo(expected);
    }
}