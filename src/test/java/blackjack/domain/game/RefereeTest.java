package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static blackjack.domain.card.CardScore.ACE;
import static blackjack.domain.card.CardScore.EIGHT;
import static blackjack.domain.card.CardScore.FIVE;
import static blackjack.domain.card.CardScore.JACK;
import static blackjack.domain.card.CardScore.KING;
import static blackjack.domain.card.CardScore.NINE;
import static blackjack.domain.card.CardScore.QUEEN;
import static blackjack.domain.card.CardScore.SEVEN;
import static blackjack.domain.card.CardScore.THREE;
import static blackjack.domain.card.CardScore.TWO;
import static blackjack.domain.card.CardSymbol.CLUB;
import static blackjack.domain.card.CardSymbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("심판")
public class RefereeTest {
    private static final String name = "lemone";
    Referee referee;
    Deck playerDeck, dealerDeck;
    private Players players;
    private Dealer dealer;
    private Map<String, GameResult> expectedPlayersResult;

    @BeforeEach
    void setupGamer() {
        players = Players.from(List.of(name));
        dealer = new Dealer();
        referee = new Referee();
        expectedPlayersResult = new HashMap<>();
    }


    @Test
    @DisplayName("심판은 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 플레이어를 승자로 여긴다.")
    void playerWinTest() {
        // given
        playerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(SPADE, NINE), new Card(CLUB, QUEEN));
            }
        };
        dealerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(SPADE, EIGHT), new Card(CLUB, QUEEN));
            }
        };
        expectedPlayersResult.put(name, GameResult.WIN);

        // when
        players.forEach(player -> player.deal(playerDeck));
        dealer.deal(dealerDeck);
        referee.calculatePlayersResults(players, dealer);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("심판은 숫자 합이 21을 초과한 플레이어를 패배자로 여긴다.")
    void playerLoseWhenBustTest() {
        // given
        playerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(SPADE, NINE), new Card(CLUB, QUEEN), new Card(CLUB, THREE));
            }
        };
        dealerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(SPADE, EIGHT), new Card(SPADE, TWO), new Card(CLUB, QUEEN));
            }
        };
        expectedPlayersResult.put(name, GameResult.LOSE);

        // when
        players.forEach(player -> player.deal(playerDeck));
        dealer.deal(dealerDeck);
        referee.calculatePlayersResults(players, dealer);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("심판은 딜러의 합과 플레이어의 합이 같으면 무승부로 본다.")
    void playerDrawWhenSameBlackjackTest() {
        // given
        playerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN), new Card(CLUB, THREE));
            }
        };
        dealerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN), new Card(CLUB, THREE));
            }
        };
        expectedPlayersResult.put(name, GameResult.DRAW);

        // when
        players.forEach(player -> player.deal(playerDeck));
        dealer.deal(dealerDeck);
        referee.calculatePlayersResults(players, dealer);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("심판은 플레이어의 첫 두개의 카드의 합이 21이면 플레이어를 승자로 여긴다.")
    void playerDealCardsBlackjackTest() {
        // given
        playerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(CLUB, ACE), new Card(CLUB, JACK));
            }
        };
        dealerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN), new Card(CLUB, FIVE));
            }
        };
        expectedPlayersResult.put(name, GameResult.WIN);

        // when
        players.forEach(player -> player.deal(playerDeck));
        dealer.deal(dealerDeck);
        referee.calculatePlayersResults(players, dealer);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("심판은 플레이어의 점수가 딜러보다 낮을 경우 플레이어를 패배자로 여긴다.")
    void playerLoseTest() {
        // given
        playerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(CLUB, TWO), new Card(CLUB, JACK));
            }
        };
        dealerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(SPADE, NINE), new Card(CLUB, SEVEN), new Card(CLUB, FIVE));
            }
        };
        expectedPlayersResult.put(name, GameResult.LOSE);

        // when
        players.forEach(player -> player.deal(playerDeck));
        dealer.deal(dealerDeck);
        referee.calculatePlayersResults(players, dealer);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("심판은 플레이어와 딜러가 모두 버스트이면, 플레이어를 패배자로 여긴다.")
    void playerDealerAllBustPlayerLoseTest() {
        // given
        playerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(CLUB, KING), new Card(CLUB, JACK), new Card(CLUB, THREE));
            }
        };
        dealerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(CLUB, KING), new Card(CLUB, JACK), new Card(CLUB, THREE));
            }
        };
        expectedPlayersResult.put(name, GameResult.LOSE);

        // when
        players.forEach(player -> player.deal(playerDeck));
        dealer.deal(dealerDeck);
        referee.calculatePlayersResults(players, dealer);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("심판은 딜러가 버스트이고 플레이가 아닐 경우, 플레이어를 승리자로 여긴다.")
    void dealerBustPlayerNonBustWinTest() {
        // given
        playerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(CLUB, KING), new Card(CLUB, JACK));
            }
        };
        dealerDeck = new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(CLUB, KING), new Card(CLUB, JACK), new Card(CLUB, THREE));
            }
        };
        expectedPlayersResult.put(name, GameResult.WIN);

        // when
        players.forEach(player -> player.deal(playerDeck));
        dealer.deal(dealerDeck);
        referee.calculatePlayersResults(players, dealer);

        // then
        assertThat(referee.getPlayersResults()).isEqualTo(expectedPlayersResult);
    }
}
