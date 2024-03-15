package cardGame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import card.CardPattern;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Player;
import player.Players;
import player.dto.CardsStatus;

class BlackJackGameTest {

    private List<Card> blackJackCards;
    private List<Card> bustCards;

    @BeforeEach
    void setUp() {
        blackJackCards = List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN));

        bustCards = List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.QUEEN, CardPattern.SPADE_PATTERN),
                new Card(CardNumber.TEN, CardPattern.SPADE_PATTERN));
    }


    @DisplayName("딜러의 카드 정보를 가져온다.")
    @Test
    void getDealerFirstCard() {
        CardDeck cardDeck = new CardDeck();

        List<Card> cards = cardDeck.firstCardSettings();
        BlackJackGame blackJackGame = new BlackJackGame(cardDeck, cards);

        assertThat(blackJackGame.getDealerFirstCard()).isEqualTo(cards.get(0));
    }

    @DisplayName("딜러가 플레이를 한 후 딜러의 카드 정보를 가져온다.")
    @Test
    void getDealerCardStatus() {
        List<String> names = List.of("pola", "jazz");
        List<Card> cards = List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN));

        BlackJackGame blackJackGame = new BlackJackGame(new CardDeck(), cards);

        CardsStatus cardsStatus = blackJackGame.playDealerTurn();
        assertAll(
                () -> assertEquals(cardsStatus.name().getValue(), "딜러"),
                () -> assertEquals(cardsStatus.cards().getFirstCard(), cards.get(0)),
                () -> assertEquals(cardsStatus.getCardsScore(), 21)
        );
    }

    @DisplayName("딜러와 플레이어가 무승부인 경우 둘다 우승자로 간주한다.")
    @Test
    void isPush() {
        List<Card> dealerCards = List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.FIVE, CardPattern.SPADE_PATTERN));

        BlackJackGame blackJackGame = new BlackJackGame(new CardDeck(), dealerCards);
        List<Card> sameCards = List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.FIVE, CardPattern.SPADE_PATTERN));

        List<Player> pushPlayers = List.of(Player.joinGame("pola", sameCards, 100),
                Player.joinGame("ato", sameCards, 100));
        Players playersGroup = new Players(pushPlayers);

        assertAll(
                () -> assertTrue(blackJackGame.getPlayersResult(playersGroup).get(0).isWinner()),
                () -> assertTrue(blackJackGame.getPlayersResult(playersGroup).get(1).isWinner()),
                () -> assertEquals(blackJackGame.getDealerResult(playersGroup).winningCount(), 2)
        );
    }

    @DisplayName("플레이어가 Bust인 경우, 딜러가 Bust 이더라도 Player는 패배하고 딜러도 패배한다.")
    @Test
    void playerBust() {
        List<Card> dealerCards = List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.QUEEN, CardPattern.SPADE_PATTERN),
                new Card(CardNumber.KING, CardPattern.CLOVER_PATTERN));

        BlackJackGame blackJackGame = new BlackJackGame(new CardDeck(), dealerCards);

        List<Player> bustPlayers = List.of(Player.joinGame("pola", bustCards, 100),
                Player.joinGame("ato", bustCards, 100));
        Players playersGroup = new Players(bustPlayers);

        assertAll(
                () -> assertFalse(blackJackGame.getPlayersResult(playersGroup).get(0).isWinner()),
                () -> assertFalse(blackJackGame.getPlayersResult(playersGroup).get(1).isWinner()),
                () -> assertEquals(blackJackGame.getDealerResult(playersGroup).winningCount(), 0)
        );
    }

    @DisplayName("딜러가 Bust인 경우 플레이어가 딜러보다 값이 작더라도 플레이어가 승리한다.")
    @Test
    void onlyDealerBust() {
        List<Card> dealerCards = List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.QUEEN, CardPattern.SPADE_PATTERN),
                new Card(CardNumber.KING, CardPattern.CLOVER_PATTERN));

        BlackJackGame blackJackGame = new BlackJackGame(new CardDeck(), dealerCards);

        List<Player> blackJackPlayer = List.of(Player.joinGame("pola", blackJackCards, 100),
                Player.joinGame("ato", blackJackCards, 100));
        Players playersGroup = new Players(blackJackPlayer);

        assertAll(
                () -> assertTrue(blackJackGame.getPlayersResult(playersGroup).get(0).isWinner()),
                () -> assertTrue(blackJackGame.getPlayersResult(playersGroup).get(1).isWinner()),
                () -> assertEquals(blackJackGame.getDealerResult(playersGroup).winningCount(), 0)
        );
    }

    @DisplayName("모두 블랙잭인 경우 무승부로 즉, 둘다 승리한다.")
    @Test
    void bothBlackJack() {
        BlackJackGame blackJackGame = new BlackJackGame(new CardDeck(), blackJackCards);

        List<Player> blackJackPlayer = List.of(Player.joinGame("pola", blackJackCards, 100),
                Player.joinGame("ato", blackJackCards, 100));
        Players playersGroup = new Players(blackJackPlayer);

        assertAll(
                () -> assertTrue(blackJackGame.getPlayersResult(playersGroup).get(0).isWinner()),
                () -> assertTrue(blackJackGame.getPlayersResult(playersGroup).get(1).isWinner()),
                () -> assertEquals(blackJackGame.getDealerResult(playersGroup).winningCount(), 2)
        );
    }

    @DisplayName("특정 상태가 아닌 경우, 21에 가까운 사람이 승리한다.")
    @Test
    void getWinner() {
        BlackJackGame blackJackGame = new BlackJackGame(new CardDeck(),
                List.of(new Card(CardNumber.TEN, CardPattern.CLOVER_PATTERN),
                        new Card(CardNumber.SEVEN, CardPattern.SPADE_PATTERN)));

        List<Player> blackJackPlayer = List.of(
                Player.joinGame("pola", List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                        new Card(CardNumber.EIGHT, CardPattern.SPADE_PATTERN)), 100),
                Player.joinGame("ato", List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                        new Card(CardNumber.FIVE, CardPattern.SPADE_PATTERN)), 100));
        Players playersGroup = new Players(blackJackPlayer);

        assertAll(
                () -> assertTrue(blackJackGame.getPlayersResult(playersGroup).get(0).isWinner()),
                () -> assertFalse(blackJackGame.getPlayersResult(playersGroup).get(1).isWinner()),
                () -> assertEquals(blackJackGame.getDealerResult(playersGroup).winningCount(), 1),
                () -> assertEquals(blackJackGame.getDealerResult(playersGroup).failCount(), 1)
        );
    }
}
