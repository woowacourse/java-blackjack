package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.Denomination;
import blackjack.card.Suit;
import blackjack.user.player.BetAmount;
import blackjack.user.dealer.Dealer;
import blackjack.user.player.Player;
import blackjack.user.player.PlayerName;
import blackjack.user.player.Players;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Nested
    @DisplayName("카드 배부 테스트")
    class DistributeCardTest {

        Players players;

        @BeforeEach
        void createBlackjackGame() {
            List<PlayerName> names = List.of(new PlayerName("sana"));
            List<Player> players = names.stream()
                .map(name -> Player.createPlayer(name, BetAmount.initAmount()))
                .toList();

            this.players = new Players(players);
        }

        @Test
        @DisplayName("딜러와 플레이어에게 카드를 2장씩 배부할 수 있다.")
        void distributeCardsToDealerAndPlayers() {
            BlackjackGame game = BlackjackGame.createGameWith(CardDeck.shuffleCardDeck(), players);

            game.initCardsToUsers();
            Dealer dealer = game.getDealer();
            Player player = game.getPlayers().getJoinedPlayers().getFirst();

            assertAll(() -> {
                assertThat(dealer.getCardHand().openCards()).hasSize(2);
                assertThat(player.getCardHand().openCards()).hasSize(2);
            });
        }

        @Test
        @DisplayName("딜러의 카드가 16이하라면 카드 한 장을 추가로 배부할 수 있다.")
        void distributeExtraCardToDealer() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.SIX),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.CLUB, Denomination.FIVE)
            ));
            CardDeck cardDeck = new CardDeck(cards);

            BlackjackGame game = BlackjackGame.createGameWith(cardDeck, players);
            Dealer dealer = game.getDealer();
            dealer.addCards(2);

            game.addExtraCardToDealer();

            assertThat(dealer.getCardHand().openCards()).hasSize(3);
        }

        @Test
        @DisplayName("딜러의 카드가 16초과라면 카드 한 장을 추가로 배부할 수 없다.")
        void notDistributeExtraCardToDealer() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.SEVEN),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.CLUB, Denomination.FIVE)
            ));
            CardDeck cardDeck = new CardDeck(cards);

            BlackjackGame game = BlackjackGame.createGameWith(cardDeck, players);
            Dealer dealer = game.getDealer();
            dealer.addCards(2);

            assertThatThrownBy(game::addExtraCardToDealer)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("더 이상 카드를 추가할 수 없습니다.");
        }

        @Test
        @DisplayName("플레이어의 카드가 21미만이라면 카드 한 장을 추가로 배부할 수 있다.")
        void distributeExtraCardToPlayer() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.CLUB, Denomination.FIVE)
            ));
            CardDeck cardDeck = new CardDeck(cards);

            BlackjackGame game = BlackjackGame.createGameWith(cardDeck, players);
            Player player = game.getPlayers().getJoinedPlayers().getFirst();
            player.addCards(game.getDealer().pickCards(2));

            game.addExtraCardToPlayer(player.getName());

            assertThat(player.getCardHand().openCards()).hasSize(3);
        }

        @Test
        @DisplayName("플레이어의 카드가 21이상이라면 카드 한 장을 추가로 배부할 수 없다.")
        void notDistributeExtraCardToPlayer() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.CLUB, Denomination.NINE),
                new Card(Suit.CLUB, Denomination.FIVE)
            ));
            CardDeck cardDeck = new CardDeck(cards);

            BlackjackGame game = BlackjackGame.createGameWith(cardDeck, players);
            Player player = game.getPlayers().getJoinedPlayers().getFirst();
            player.addCards(game.getDealer().pickCards(3));

            assertThatThrownBy(() -> game.addExtraCardToPlayer(player.getName()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("더 이상 카드를 추가할 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("수익률 계산 테스트")
    class WinOrLoseStatistics {

        BlackjackGame game;

        @BeforeEach
        void initCards() {
            List<Player> players = List.of(
                Player.createPlayer(new PlayerName("hula"), // 패
                    BetAmount.initAmountWithPrincipal(10000)),
                Player.createPlayer(new PlayerName("sana"), // 승(블랙잭)
                    BetAmount.initAmountWithPrincipal(10000)),
                Player.createPlayer(new PlayerName("jason"),
                    BetAmount.initAmountWithPrincipal(10000)) // 패
            );

            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.NINE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.SIX),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.FIVE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            game = BlackjackGame.createGameWith(cardDeck, new Players(players));
            game.initCardsToUsers(); // 2장씩 배부
        }

        @Test
        @DisplayName("플레이어의 수익을 계산할 수 있다.")
        void calculatePlayerStatistics() {
            int totalProfit = game.calculateProfitForPlayers();

            Player player1 = game.getPlayers().getJoinedPlayers().get(0);
            Player player2 = game.getPlayers().getJoinedPlayers().get(1);
            Player player3 = game.getPlayers().getJoinedPlayers().get(2);

            assertAll(() -> {
                assertThat(player1.getBetAmount().getProfit()).isEqualTo(-10000);
                assertThat(player2.getBetAmount().getProfit()).isEqualTo(15000);
                assertThat(player3.getBetAmount().getProfit()).isEqualTo(-10000);
                assertThat(totalProfit).isEqualTo(-5000);
            });
        }
    }
}
