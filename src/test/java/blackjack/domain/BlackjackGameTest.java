package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@Nested
class BlackjackGameTest {

    @Nested
    @DisplayName("플레이어 여러명 생성 테스트")
    class CreateParticipantsTest {

        @Test
        @DisplayName("2명 이상의 플레이어를 입력 받을 수 있다.")
        void createParticipantsByNames() {
            List<String> names = List.of("hula", "sana");
            BlackjackGame game = BlackjackGame.createByPlayerNames(names);

            List<String> playerNames = game.getPlayerNames();

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
            BlackjackGame game = BlackjackGame.createByPlayerNames(names);

            assertThatCode(game::initCardsToParticipants)
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("카드 한 장을 플레이어에게 추가로 배부할 수 있다.")
        void distributeExtraCardToPlayer() {
            List<String> names = List.of("sana");
            BlackjackGame game = BlackjackGame.createByPlayerNames(names);
            game.initCardsToParticipants(); // 2장 배부

            Player player = (Player) game.getParticipants().getLast();
            game.addExtraCard(player); // 총 3장 카드 보유

            assertThat(player.getCards()).hasSize(3);
        }

        @Test
        @DisplayName("카드 한 장을 딜러의 숫자가 16이하이면 추가로 배부할 수 있다.")
        void distributeExtraCardToDealer() {
            Dealer dealer = new Dealer();
            Card card1 = new Card(Suit.HEART, Denomination.SIX);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);

            dealer.addCards(card1, card2);

            CardDeck cardDeck = CardDeck.createCardDeck();
            List<Participant> participants = List.of(dealer);
            BlackjackGame game = new BlackjackGame(cardDeck, participants);

            assertThat(game.addExtraCardToDealer()).isTrue();
        }

        @Test
        @DisplayName("카드 한 장을 딜러의 숫자가 16초과이면 추가로 배부할 수 없다.")
        void notDistributeExtraCardToDealer() {
            Dealer dealer = new Dealer();
            Card card1 = new Card(Suit.HEART, Denomination.SEVEN);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);

            dealer.addCards(card1, card2);

            CardDeck cardDeck = CardDeck.createCardDeck();
            List<Participant> participants = List.of(dealer);
            BlackjackGame game = new BlackjackGame(cardDeck, participants);

            assertThat(game.addExtraCardToDealer()).isFalse();
        }
    }

    @Nested
    @DisplayName("승패 통계 테스트")
    class WinOrLoseStatistics {

        BlackjackGame game;

        @BeforeEach
        void initCards() {
            Dealer dealer = new Dealer();
            Card card1_dealer = new Card(Suit.HEART, Denomination.NINE);
            Card card2_dealer = new Card(Suit.SPADE, Denomination.KING);
            dealer.addCards(card1_dealer, card2_dealer);

            Player player1 = new Player("hula"); // 패배
            Card card1_player1 = new Card(Suit.HEART, Denomination.SIX);
            Card card2_player1 = new Card(Suit.SPADE, Denomination.KING);
            player1.addCards(card1_player1, card2_player1);

            Player player2 = new Player("sana"); // 승리
            Card card1_player2 = new Card(Suit.HEART, Denomination.ACE);
            Card card2_player2 = new Card(Suit.SPADE, Denomination.KING);
            player2.addCards(card1_player2, card2_player2);

            Player player3 = new Player("jason"); // 패배
            Card card1_player3 = new Card(Suit.HEART, Denomination.FIVE);
            Card card2_player3 = new Card(Suit.SPADE, Denomination.KING);
            player3.addCards(card1_player3, card2_player3);

            CardDeck cardDeck = CardDeck.createCardDeck();
            List<Participant> participants = List.of(dealer, player1, player2, player3);
            game = new BlackjackGame(cardDeck, participants);
        }

        @Test
        @DisplayName("플레이어의 승패 통계를 계산할 수 있다.")
        void calculatePlayerStatistics() {
            Map<Player, GameResult> playerResult = game.calculateStatisticsForPlayer();
            List<Player> players = game.findPlayers();
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
