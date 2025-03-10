package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
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
            List<String> names = List.of("hula", "sana");
            BlackjackGame game = BlackjackGame.createByPlayerNames(CardDeck.shuffleCardDeck(), names);

            List<String> playerNames = game.getParticipants().getPlayerNames();

            assertAll(() -> {
                assertThat(playerNames.getFirst()).isEqualTo(names.getFirst());
                assertThat(playerNames.getLast()).isEqualTo(names.getLast());
            });
        }
    }

    @Nested
    @DisplayName("카드 배부 테스트")
    class DistributeCardTest {

        @Test
        @DisplayName("딜러와 플레이어에게 카드를 2장씩 배부할 수 있다.")
        void distributeCardsToDealerAndPlayer() {
            List<String> names = List.of("sana");
            BlackjackGame game = BlackjackGame.createByPlayerNames(CardDeck.shuffleCardDeck(),
                names);

            assertAll(() -> {
                assertThatCode(game::initCardsToDealer).doesNotThrowAnyException();
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

            BlackjackGame game = BlackjackGame.createByPlayerNames(cardDeck, List.of("sana"));
            Player player = game.getParticipants().getPlayers().getFirst();

            player.addCards(cardDeck, 2);
            game.addExtraCard(player); // 총 3장 카드 보유

            assertThat(player.openCards()).hasSize(3);
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

            BlackjackGame game = BlackjackGame.createByPlayerNames(cardDeck, List.of("sana"));
            Dealer dealer = game.getParticipants().getDealer();

            dealer.addCards(cardDeck, 2);
            game.addExtraCard(dealer);

            assertThat(dealer.openCards()).hasSize(3);
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

            BlackjackGame game = BlackjackGame.createByPlayerNames(cardDeck, List.of("sana"));
            Dealer dealer = game.getParticipants().getDealer();

            dealer.addCards(cardDeck, 2);

            assertThatThrownBy(() -> game.addExtraCard(dealer))
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
            game = BlackjackGame.createByPlayerNames(cardDeck, List.of("hula", "sana", "jason"));

            Dealer dealer = game.getParticipants().getDealer();
            dealer.addCards(cardDeck, 2);

            List<Player> players = game.getParticipants().getPlayers();
            for (Player player : players) {
                player.addCards(cardDeck, 2);
            }
        }

        @Test
        @DisplayName("플레이어의 승패 통계를 계산할 수 있다.")
        void calculatePlayerStatistics() {
            Map<Player, GameResult> playerResult = game.calculateStatisticsForPlayer();
            List<Player> players = game.getParticipants().getPlayers();
            Player player1 = players.get(0);
            Player player2 = players.get(1);
            Player player3 = players.get(2);

            assertAll(() -> {
                assertThat(playerResult.get(player1)).isEqualTo(GameResult.LOSE);
                assertThat(playerResult.get(player2)).isEqualTo(GameResult.WIN);
                assertThat(playerResult.get(player3)).isEqualTo(GameResult.LOSE);
            });
        }

        @Test
        @DisplayName("딜러의 승패 통계를 계산할 수 있다.")
        void calculateDealerStatistics() {
            Map<GameResult, Integer> result = game.calculateStatisticsForDealer();
            assertAll(() -> {
                assertThat(result.get(GameResult.WIN)).isEqualTo(2);
                assertThat(result.get(GameResult.LOSE)).isEqualTo(1);
            });
        }
    }
}
