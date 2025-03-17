package blackjack.user.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.Denomination;
import blackjack.card.Suit;
import blackjack.user.dealer.Dealer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Nested
    @DisplayName("플레이어 생성 테스트")
    class CreatePlayerNameTest {

        @Test
        @DisplayName("2명 이상의 플레이어를 입력 받을 수 있다.")
        void createPlayersOverTwo() {
            List<Player> players = List.of(
                Player.createPlayer(new PlayerName("hula"), BetAmount.initAmount()),
                Player.createPlayer(new PlayerName("sana"), BetAmount.initAmount())
            );

            assertDoesNotThrow(() -> new Players(players));
        }

        @Test
        @DisplayName("2명 미만의 플레이어를 입력 받을 수 없다.")
        void createPlayersUnderTwo() {
            List<Player> players = new ArrayList<>();

            assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 한 명 이상 참가해야 합니다.");
        }

        @Test
        @DisplayName("25명의 참가자는 게임을 진행할 수 없다.")
        void createParticipantsOver25() {
            List<Player> players = IntStream.rangeClosed('a', 'z')
                .mapToObj(
                    c -> Player.createPlayer(new PlayerName(String.valueOf((char) c)),
                        BetAmount.initAmount()))
                .toList();

            assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 25명까지만 참가 가능합니다.");
        }

        @Test
        @DisplayName("중복된 이름이 없는 경우 참가자를 생성할 수 있다.")
        void createParticipantsWithNotDuplicate() {
            List<Player> players = List.of(
                Player.createPlayer(new PlayerName("a"), BetAmount.initAmount()),
                Player.createPlayer(new PlayerName("b"), BetAmount.initAmount()),
                Player.createPlayer(new PlayerName("c"), BetAmount.initAmount())
            );

            assertDoesNotThrow(() -> new Players(players));
        }

        @Test
        @DisplayName("중복된 이름이 없는 경우 참가자를 생성할 수 없다.")
        void createParticipantsWithDuplicate() {
            List<Player> players = List.of(
                Player.createPlayer(new PlayerName("a"), BetAmount.initAmount()),
                Player.createPlayer(new PlayerName("a"), BetAmount.initAmount()),
                Player.createPlayer(new PlayerName("c"), BetAmount.initAmount())
            );

            assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름을 가진 플레이어가 있습니다.");
        }
    }

    @Nested
    @DisplayName("카드 배부 테스트")
    class DistributeCardTest {

        @Test
        @DisplayName("딜러로부터 카드를 배부받을 수 있다.")
        void distributeCardsFromDealer() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            List<Player> playerList = List.of(
                Player.createPlayer(new PlayerName("hula"), BetAmount.initAmount()),
                Player.createPlayer(new PlayerName("sana"), BetAmount.initAmount())
            );
            Players players = new Players(playerList);
            players.addPickedCards(dealer, 2);

            List<Player> result = players.getJoinedPlayers();

            assertAll(() -> {
                assertThat(result.getFirst().getCardHand().openCards()).hasSize(2);
                assertThat(result.getLast().getCardHand().openCards()).hasSize(2);
            });
        }


        @Test
        @DisplayName("딜러로부터 추가 카드를 1장씩 배부할 수 있다.")
        void distributeExtraCardsToDealer() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.SPADE, Denomination.THREE),
                new Card(Suit.SPADE, Denomination.FIVE)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            List<Player> playerList = List.of(
                Player.createPlayer(new PlayerName("hula"), BetAmount.initAmount()),
                Player.createPlayer(new PlayerName("sana"), BetAmount.initAmount())
            );
            Players players = new Players(playerList);
            players.addPickedCards(dealer, 2);
            players.addExtraCardToPlayer(dealer, new PlayerName("hula"), 1);
            players.addExtraCardToPlayer(dealer, new PlayerName("sana"), 1);

            List<Player> result = players.getJoinedPlayers();

            assertAll(() -> {
                assertThat(result.getFirst().getCardHand().openCards()).hasSize(3);
                assertThat(result.getLast().getCardHand().openCards()).hasSize(3);
            });
        }
    }

    @Nested
    @DisplayName("수익률 총합 테스트")
    class ProfitTest {

        @Test
        @DisplayName("플레이어의 총 수익을 계산할 수 있다.")
        void calculateTotalProfit() {
            List<Player> playerList = List.of(
                Player.createPlayer(new PlayerName("hula"), // 패
                    BetAmount.initAmountWithPrincipal(10000)),
                Player.createPlayer(new PlayerName("sana"), // 승(블랙잭)
                    BetAmount.initAmountWithPrincipal(10000)),
                Player.createPlayer(new PlayerName("jason"),
                    BetAmount.initAmountWithPrincipal(10000)) // 무
            );

            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.NINE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.SIX),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.HEART, Denomination.NINE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);
            Players players = new Players(playerList);

            dealer.addCards(2);
            players.addPickedCards(dealer, 2);
            players.calculatePlayersProfit(dealer);

            List<Player> result = players.getJoinedPlayers();

            assertAll(() -> {
                assertThat(result.get(0).getBetAmount().getProfit()).isEqualTo(-10000);
                assertThat(result.get(1).getBetAmount().getProfit()).isEqualTo(15000);
                assertThat(result.get(2).getBetAmount().getProfit()).isEqualTo(0);
            });
        }
    }
}
