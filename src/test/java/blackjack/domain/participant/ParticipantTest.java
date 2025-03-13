package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantTest {

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
                return false;
            }
        };
    }

    @Nested
    @DisplayName("카드 배부 테스트")
    class CardDistributeTest {

        @Test
        @DisplayName("참가자는 카드를 여러장 배부 받을 수 있다.")
        void distributeTwoCards() {
            Card card1 = new Card(Suit.HEART, Denomination.ACE);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);

            participant.addCards(card1, card2);

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
            Card card1 = new Card(Suit.HEART, Denomination.TWO);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);

            participant.addCards(card1, card2);

            int cardSum = participant.calculateDenominations();

            assertThat(cardSum).isEqualTo(12);
        }

        @Test
        @DisplayName("에이스를 11로 계산한다.")
        void cardSumWithACE_ELEVEN() {
            Card card1 = new Card(Suit.HEART, Denomination.ACE);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);

            participant.addCards(card1, card2);

            int cardSumWithAceValue11 = participant.calculateDenominations();

            assertThat(cardSumWithAceValue11).isEqualTo(21);
        }

        @Test
        @DisplayName("에이스를 1로 계산한다.")
        void cardSumWithACE_ONE() {
            Card card1 = new Card(Suit.HEART, Denomination.TWO);
            Card card2 = new Card(Suit.SPADE, Denomination.TEN);
            Card card3 = new Card(Suit.CLUB, Denomination.ACE);

            participant.addCards(card1, card2, card3);

            int cardSumWithAceValue1 = participant.calculateDenominations();

            assertThat(cardSumWithAceValue1).isEqualTo(13);
        }
    }
}
