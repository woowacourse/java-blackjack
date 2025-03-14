package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.Denomination;
import blackjack.card.Suit;
import blackjack.game.betting.BetAmount;
import blackjack.user.Dealer;
import blackjack.user.Player;
import blackjack.user.PlayerName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Nested
    @DisplayName("플레이어 여러명 생성 테스트")
    class CreateParticipantsTest {

        @Test
        @DisplayName("2명 이상의 플레이어를 입력 받을 수 있다.")
        void createParticipantsByNames() {
            List<PlayerName> names = List.of(
                new PlayerName("hula"),
                new PlayerName("sana")
            );

            BlackjackGame game = BlackjackGame.createWithEmptyBet(CardDeck.shuffleCardDeck(),
                names);
            List<String> playerNames = game.getParticipants().getPlayerNames();

            assertAll(() -> {
                assertThat(playerNames.getFirst()).isEqualTo("hula");
                assertThat(playerNames.getLast()).isEqualTo("sana");
            });
        }
    }

    @Nested
    @DisplayName("카드 배부 테스트")
    class DistributeCardTest {

        @Test
        @DisplayName("딜러와 플레이어에게 카드를 2장씩 배부할 수 있다.")
        void distributeCardsToDealerAndPlayer() {
            List<PlayerName> names = List.of(new PlayerName("sana"));
            BlackjackGame game = BlackjackGame.createWithEmptyBet(CardDeck.shuffleCardDeck(),
                names);

            assertAll(() -> {
                assertThatCode(game::initCardsToParticipants).doesNotThrowAnyException();
                assertThatCode(game::initCardsToPlayer).doesNotThrowAnyException();
            });
        }

        @Test
        @DisplayName("카드 한 장을 플레이어에게 추가로 배부할 수 있다.")
        void distributeExtraCardToPlayer() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.CLUB, Denomination.FIVE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            List<PlayerName> names = List.of(new PlayerName("sana"));

            BlackjackGame game = BlackjackGame.createWithEmptyBet(cardDeck, names);
            Player player = game.getParticipants().getPlayers().getFirst();

            player.addCards(cardDeck, 2);
            game.addExtraCardToPlayer(player); // 총 3장 카드 보유

            assertThat(player.getCards().openCards()).hasSize(3);
        }

        @Test
        @DisplayName("카드 한 장을 딜러의 숫자가 16이하이면 추가로 배부할 수 있다.")
        void distributeExtraCardToDealer() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.SIX),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.CLUB, Denomination.FIVE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            List<PlayerName> names = List.of(new PlayerName("sana"));

            BlackjackGame game = BlackjackGame.createWithEmptyBet(cardDeck, names);
            game.initCardsToParticipants();
            game.addExtraCardToDealer();

            assertThat(game.getParticipants().getDealer().getCards().openCards()).hasSize(3);
        }

        @Test
        @DisplayName("카드 한 장을 딜러의 숫자가 16초과이면 추가로 배부할 수 없다.")
        void notDistributeExtraCardToDealer() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.SEVEN),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.CLUB, Denomination.FIVE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            List<PlayerName> names = List.of(new PlayerName("sana"));

            BlackjackGame game = BlackjackGame.createWithEmptyBet(cardDeck, names);
            Dealer dealer = game.getParticipants().getDealer();

            dealer.addCards(cardDeck, 2);

            assertThatThrownBy(() -> game.addExtraCardToDealer())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("더 이상 카드를 추가할 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("승패 통계 테스트")
    class WinOrLoseStatistics {

        BlackjackGame game;

        @BeforeEach
        void initCards() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.NINE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.SIX),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.FIVE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);
            Map<PlayerName, BetAmount> playerWallet = Map.of(
                new PlayerName("hula"), BetAmount.initialBetting(10000), // 패
                new PlayerName("sana"), BetAmount.initialBetting(10000), // 승(블랙잭)
                new PlayerName("jason"), BetAmount.initialBetting(10000) // 패
            );
            game = BlackjackGame.createByBettingPlayers(cardDeck, playerWallet);

            Dealer dealer = game.getParticipants().getDealer();
            dealer.addCards(cardDeck, 2);

            List<Player> players = game.getParticipants().getPlayers();
            for (Player player : players) {
                player.addCards(cardDeck, 2);
            }
        }

        @Test
        @DisplayName("플레이어의 수익을 계산할 수 있다.")
        void calculatePlayerStatistics() {
            int totalProfit = game.calculateProfitForPlayer();

            assertThat(totalProfit).isEqualTo(-5000);
        }
    }
}
