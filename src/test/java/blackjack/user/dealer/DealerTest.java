package blackjack.user.dealer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.Denomination;
import blackjack.card.Suit;
import blackjack.user.player.BetAmount;
import blackjack.user.player.Player;
import blackjack.user.player.PlayerName;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Nested
    @DisplayName("카드 뽑기 테스트")
    class PickCardTest {

        @Test
        @DisplayName("카드덱에서 카드를 원하는 수만큼 뽑을 수 있다.")
        void pickCard() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.FIVE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            List<Card> pickedCards = dealer.pickCards(2);

            assertThat(pickedCards).hasSize(2);
        }
    }

    @Nested
    @DisplayName("딜러 카드 추가 테스트")
    class AddCardTest {

        @Test
        @DisplayName("카드의 합이 16 이하이면 카드를 배부 받을 수 있다.")
        void addCard_SumUnder16() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.FIVE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            dealer.addCards(2);

            assertThat(dealer.getCardHand().openCards()).hasSize(2);
        }

        @Test
        @DisplayName("카드의 합이 16 초과면 카드를 배부 받을 수 없다.")
        void addCard_SumOver16() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.SEVEN),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.SPADE, Denomination.FIVE)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            dealer.addCards(2);

            assertThatThrownBy(() -> dealer.addCards(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("더 이상 카드를 추가할 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("카드 오픈 테스트")
    class OpenCardTest {

        @Test
        @DisplayName("딜러는 초기 카드를 한 장만 오픈할 수 있다.")
        void openFirstCard() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            dealer.addCards(2);
            List<Card> result = dealer.openInitialCards();

            assertAll(() -> {
                assertThat(result).hasSize(1);
                assertThat(result.getFirst()).isEqualTo(new Card(Suit.HEART, Denomination.ACE));
            });
        }
    }

    @Nested
    @DisplayName("수익 계산 테스트")
    class MatchResultTest {

        Player player;

        @BeforeEach
        void initPlayer() {
            player = Player.createPlayer(new PlayerName("sana"),
                BetAmount.initAmountWithPrincipal(10000));
        }

        @Test
        @DisplayName("블랙잭이면 원금의 1.5배의 이익을 얻는다")
        void winWithBlackjack() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.CLUB, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.THREE),
                new Card(Suit.CLUB, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.ACE)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            dealer.addCards(2);
            player.addCards(dealer.pickCards(2));

            assertThat(dealer.calculateProfitForPlayer(player)).isEqualTo(15000);
        }

        @Test
        @DisplayName("블랙잭이 아닌 승리는 원금 만큼의 이익을 얻는다")
        void winWithNotBlackjack() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.FIVE),
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.CLUB, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.THREE),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            dealer.addCards(2);
            player.addCards(dealer.pickCards(2));

            assertThat(dealer.calculateProfitForPlayer(player)).isEqualTo(10000);
        }

        @Test
        @DisplayName("무승부는 이익이 없다")
        void draw() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.CLUB, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            dealer.addCards(2);
            player.addCards(dealer.pickCards(2));

            assertThat(dealer.calculateProfitForPlayer(player)).isEqualTo(0);
        }

        @Test
        @DisplayName("패배는 원금 만큼을 잃는다")
        void lose() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.ACE),
                new Card(Suit.CLUB, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.TWO),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            dealer.addCards(2);
            player.addCards(dealer.pickCards(2));

            assertThat(dealer.calculateProfitForPlayer(player)).isEqualTo(-10000);
        }
    }
}
