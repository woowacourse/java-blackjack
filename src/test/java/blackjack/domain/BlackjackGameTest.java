package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Payout;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerName;
import blackjack.domain.participant.Players;
import java.util.List;
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
            BlackjackGame game = BlackjackGame.createByPlayerNames(names);

            List<String> playerNames = game.getPlayers()
                    .stream()
                    .map(Player::getName)
                    .toList();

            assertAll(() -> {
                assertThat(playerNames.getFirst()).isEqualTo(names.getFirst());
                assertThat(playerNames.getLast()).isEqualTo(names.getLast());
            });
        }
    }

    @Nested
    @DisplayName("카드 배부 테스트")
    class DistributeCardTest {

        Players defaultPlayers = new Players(
                List.of(new Player(new PlayerName("hula")), new Player(new PlayerName("sana")))
        );

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

            Player player = game.getPlayers().getFirst();
            game.addExtraCard(player); // 총 3장 카드 보유

            assertThat(player.openCards()).hasSize(3);
        }

        @Test
        @DisplayName("카드 한 장을 딜러의 숫자가 16이하이면 추가로 배부할 수 있다.")
        void distributeExtraCardToDealer() {
            Dealer dealer = new Dealer();
            List<Card> cardsUnder16 = List.of(
                    new Card(Suit.HEART, Denomination.SIX),
                    new Card(Suit.SPADE, Denomination.KING)
            );
            dealer.addCards(cardsUnder16.get(0), cardsUnder16.get(1));

            CardDeck cardDeck = CardDeck.createCardDeck();
            BlackjackGame game = new BlackjackGame(cardDeck, dealer, defaultPlayers);

            assertThat(game.addExtraCardToDealer()).isTrue();
        }

        @Test
        @DisplayName("카드 한 장을 딜러의 숫자가 16초과이면 추가로 배부할 수 없다.")
        void notDistributeExtraCardToDealer() {
            Dealer dealer = new Dealer();
            List<Card> cardsOver16 = List.of(
                    new Card(Suit.HEART, Denomination.SEVEN),
                    new Card(Suit.SPADE, Denomination.KING)
            );
            dealer.addCards(cardsOver16.get(0), cardsOver16.get(1));

            CardDeck cardDeck = CardDeck.createCardDeck();
            BlackjackGame game = new BlackjackGame(cardDeck, dealer, defaultPlayers);

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
            List<Card> initialCards1 = List.of(
                    new Card(Suit.HEART, Denomination.NINE),
                    new Card(Suit.SPADE, Denomination.KING)
            );
            dealer.addCards(initialCards1.get(0), initialCards1.get(1));

            Player player1 = new Player(new PlayerName("hula")); // 패배
            List<Card> initialCards2 = List.of(
                    new Card(Suit.HEART, Denomination.SIX),
                    new Card(Suit.SPADE, Denomination.KING)
            );
            player1.addCards(initialCards2.get(0), initialCards2.get(1));

            Player player2 = new Player(new PlayerName("sana")); // 승리
            List<Card> initialCards3 = List.of(
                    new Card(Suit.HEART, Denomination.ACE),
                    new Card(Suit.SPADE, Denomination.KING)
            );
            player2.addCards(initialCards3.get(0), initialCards3.get(1));

            Player player3 = new Player(new PlayerName("jason")); // 패배
            List<Card> initialCards4 = List.of(
                    new Card(Suit.HEART, Denomination.FIVE),
                    new Card(Suit.SPADE, Denomination.KING)
            );
            player3.addCards(initialCards4.get(0), initialCards4.get(1));

            CardDeck cardDeck = CardDeck.createCardDeck();
            List<Player> players = List.of(player1, player2, player3);
            game = new BlackjackGame(cardDeck, dealer, new Players(players));
        }
    }

    @Nested
    @DisplayName("딜러의 베팅 금액 테스트")
    class DealerBetTest {

        @Test
        @DisplayName("딜러의 베팅 금액 수익을 계산할 수 있다")
        void calculateDealerBetAmount() {
            Dealer dealer = new Dealer();
            dealer.addCards(
                    new Card(Suit.CLUB, Denomination.EIGHT),
                    new Card(Suit.SPADE, Denomination.ACE)
            );

            Player player1 = new Player(new PlayerName("hula"));
            player1.bet(2000);  // 승리 (블랙잭)
            player1.addCards(
                    new Card(Suit.SPADE, Denomination.ACE),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Player player2 = new Player(new PlayerName("sana"));
            player2.bet(1000);  // 승리
            player2.addCards(
                    new Card(Suit.SPADE, Denomination.QUEEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Player player3 = new Player(new PlayerName("pppk"));
            player3.bet(5000);  // 패배 (버스트)
            player3.addCards(
                    new Card(Suit.HEART, Denomination.TWO),
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Players players = new Players(List.of(player1, player2, player3));
            BlackjackGame game = new BlackjackGame(
                    CardDeck.createCardDeck(),
                    dealer,
                    players
            );

            assertThat(game.calculateDealerPayout()).isEqualTo(new Payout(1000));
        }
    }
}
