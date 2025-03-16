package blackjack.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.CardHand;
import blackjack.card.Denomination;
import blackjack.game.GameResult;
import blackjack.card.Suit;
import blackjack.user.dealer.Dealer;
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
    @DisplayName("카드 오픈 테스트")
    class OpenCardTest {

        @Test
        @DisplayName("딜러는 초기 카드를 한 장만 오픈할 수 있다.")
        void openFirstCard() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Dealer dealer = new Dealer(new CardHand(17));
            dealer.addCards(cardDeck, 2);

            List<Card> cards = dealer.openInitialCards();
            assertAll(() -> {
                assertThat(cards).hasSize(1);
                assertThat(cards.getFirst()).isEqualTo(new Card(Suit.HEART, Denomination.ACE));
            });
        }
    }

    @Nested
    @DisplayName("딜러 추가 배부 테스트")
    class ExtraCardTest {

        @Test
        @DisplayName("카드의 합이 16 이하이면 추가 배부 받을 수 있다.")
        void extraCard_SumUnder16() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.FIVE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Dealer dealer = new Dealer(new CardHand(17));
            dealer.addCards(cardDeck, 2);

            assertThat(dealer.isPossibleToAdd()).isTrue();
        }

        @Test
        @DisplayName("카드의 합이 16 초과면 추가 배부 받을 수 없다.")
        void extraCard_SumOver16() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.SEVEN),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Dealer dealer = new Dealer(new CardHand(17));
            dealer.addCards(cardDeck, 2);

            assertThat(dealer.isPossibleToAdd()).isFalse();
        }
    }

    @Nested
    @DisplayName("승패 결정 테스트")
    class MatchResultTest {

        Player player;
        Dealer dealer;

        @BeforeEach
        void initParticipants() {
            player = new Player(new PlayerName("sana"), new CardHand(21));
            dealer = new Dealer(new CardHand(17));
        }

        @Test
        @DisplayName("블랙잭 vs 블랙잭이면 무승부를 반환한다.")
        void matchBlackJackVsBlackJack() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.CLUB, Denomination.ACE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            player.addCards(cardDeck, 2);
            dealer.addCards(cardDeck, 2);

            assertThat(dealer.judgePlayerResult(player)).isEqualTo(GameResult.DRAW);
        }

        @Test
        @DisplayName("버스트 vs 버스트이면 플레이어는 패배를 반환한다.")
        void matchBustVsBust() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.KING),
                new Card(Suit.CLUB, Denomination.THREE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.SPADE, Denomination.NINE),
                new Card(Suit.CLUB, Denomination.FIVE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            player.addCards(cardDeck, 3);
            dealer.addCards(cardDeck, 3);

            assertThat(dealer.judgePlayerResult(player)).isEqualTo(GameResult.LOSE);
        }

        @Test
        @DisplayName("딜러가 블랙잭이 아니고 플레이어가 블랙잭이면 승리를 반환한다.")
        void matchNotBlackJackVsBlackJack() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.SPADE, Denomination.NINE),
                new Card(Suit.CLUB, Denomination.TWO)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            player.addCards(cardDeck, 2);
            dealer.addCards(cardDeck, 3);

            assertThat(dealer.judgePlayerResult(player)).isEqualTo(GameResult.WIN);
        }

        @Test
        @DisplayName("블랙잭과 버스트가 모두 아니라면 숫자를 비교해 21에 가까우면 이긴다.")
        void matchOthersVsOthers() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.CLUB, Denomination.NINE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.SPADE, Denomination.FIVE),
                new Card(Suit.CLUB, Denomination.TWO)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            player.addCards(cardDeck, 2);
            dealer.addCards(cardDeck, 3);

            assertThat(dealer.judgePlayerResult(player)).isEqualTo(GameResult.WIN);
        }
    }
}
