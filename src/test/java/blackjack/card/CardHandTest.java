package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CardHandTest {

    @Nested
    @DisplayName("카드 배부 테스트")
    class CardDistributeTest {

        @Test
        @DisplayName("카드를 여러장 배부 받을 수 있다.")
        void distributeTwoCards() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            CardHand cardHand = new CardHand(21);
            cardHand.addCards(cardDeck, 2);
            List<Card> cards = cardHand.openCards();

            assertAll(() -> {
                assertThat(cards).hasSize(2);
                assertThat(cards.getFirst().suit()).isEqualTo(Suit.HEART);
                assertThat(cards.getFirst().denomination()).isEqualTo(Denomination.ACE);
                assertThat(cards.getLast().suit()).isEqualTo(Suit.SPADE);
                assertThat(cards.getLast().denomination()).isEqualTo(Denomination.KING);
            });
        }
    }

    @Nested
    @DisplayName("카드 상태 테스트")
    class CardStatusTest {

        @Test
        @DisplayName("처음 두 장의 카드가 21인 경우 블랙잭이다.")
        void checkBlackjack() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            CardHand cardHand = new CardHand(21);
            cardHand.addCards(cardDeck, 2);

            assertThat(cardHand.isBlackjack()).isTrue();
        }

        @Test
        @DisplayName("카드의 합이 21을 넘는 경우 버스트이다.")
        void checkBust() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.SIX),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.SPADE, Denomination.SEVEN)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            CardHand cardHand = new CardHand(21);
            cardHand.addCards(cardDeck, 3);

            assertThat(cardHand.isBust()).isTrue();
        }
    }

    @Nested
    @DisplayName("카드의 총합 테스트")
    class CardSumTest {

        @Test
        @DisplayName("에이스가 없을 시 단순 합을 계산한다.")
        void cardSumWithoutACE() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            CardHand cardHand = new CardHand(21);
            cardHand.addCards(cardDeck, 2);
            int cardSum = cardHand.calculateDenominations();

            assertThat(cardSum).isEqualTo(12);
        }

        @Test
        @DisplayName("에이스를 11로 계산한다.")
        void cardSumWithACE_ELEVEN() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            CardHand cardHand = new CardHand(21);
            cardHand.addCards(cardDeck, 2);
            int cardSumWithAceValue11 = cardHand.calculateDenominations();

            assertThat(cardSumWithAceValue11).isEqualTo(21);
        }

        @Test
        @DisplayName("에이스를 1로 계산한다.")
        void cardSumWithACE_ONE() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.ACE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            CardHand cardHand = new CardHand(21);
            cardHand.addCards(cardDeck, 3);
            int cardSumWithAceValue1 = cardHand.calculateDenominations();

            assertThat(cardSumWithAceValue1).isEqualTo(13);
        }
    }

    @Nested
    @DisplayName("카드 공개 테스트")
    class OpenCardTest {

        @Test
        @DisplayName("모든 카드를 공개 가능하다.")
        void openAllCards() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            CardHand cardHand = new CardHand(21);
            cardHand.addCards(cardDeck, 2);

            assertThat(cardHand.openCards()).hasSize(2);
        }

        @Test
        @DisplayName("처음 배부 카드는원하는 개수만큼 공개 가능하다.")
        void openCardsWithCount() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.SIX),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.SPADE, Denomination.SEVEN)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            CardHand cardHand = new CardHand(21);
            cardHand.addCards(cardDeck, 3);

            assertThat(cardHand.openInitialCards(2)).hasSize(2);
        }
    }

    @Nested
    @DisplayName("카드 추가 테스트")
    class CardAddTest {

        @Test
        @DisplayName("기준치를 넘지 않으면 카드를 추가할 수 있다.")
        void checkPossibleToAdd() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            CardHand cardHand = new CardHand(21);
            cardHand.addCards(cardDeck, 2);

            assertThat(cardHand.isPossibleToAdd()).isTrue();
        }
    }
}
