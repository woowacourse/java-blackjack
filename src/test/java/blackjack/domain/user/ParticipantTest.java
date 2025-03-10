package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.Card;
import blackjack.domain.CardDeck;
import blackjack.domain.Denomination;
import blackjack.domain.Suit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ParticipantTest {

    Participant participant;

    @BeforeEach
    void initParticipant() {
        participant = new Participant() {
            @Override
            public List<Card> openInitialCards() {
                return super.openCards();
            }

            @Override
            public boolean isPossibleToAdd() {
                return true;
            }
        };
    }

    @Nested
    @DisplayName("카드 배부 테스트")
    class CardDistributeTest {

        @Test
        @DisplayName("참가자는 카드를 여러장 배부 받을 수 있다.")
        void distributeTwoCards() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            participant.addCards(cardDeck, 2);
            List<Card> cards = participant.openCards();

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

            participant.addCards(cardDeck, 2);
            int cardSum = participant.calculateDenominations();

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

            participant.addCards(cardDeck, 2);
            int cardSumWithAceValue11 = participant.calculateDenominations();

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

            participant.addCards(cardDeck, 3);
            int cardSumWithAceValue1 = participant.calculateDenominations();

            assertThat(cardSumWithAceValue1).isEqualTo(13);
        }
    }
}
