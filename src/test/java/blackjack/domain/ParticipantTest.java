package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
public class ParticipantTest {

    @Nested
    @DisplayName("카드 배부 테스트")
    class CardDistributeTest {

        @Test
        @DisplayName("참가자는 카드 두 장을 배부 받을 수 있다.")
        void distributeTwoCards() {
            Participant participant = new Participant();
            Card card1 = new Card(Suit.HEART, Denomination.ACE);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);

            participant.addCard(card1);
            participant.addCard(card2);
            List<Card> cards = participant.getCards();

            assertAll(() -> {
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
        @DisplayName("에이스가 없을 시, 카드의 합을 계산한다.")
        void cardSumWithoutACE() {
            Participant participant = new Participant();
            Card card1 = new Card(Suit.HEART, Denomination.TWO);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);

            participant.addCard(card1);
            participant.addCard(card2);
            int sum = participant.calculateDenominations();

            assertThat(sum).isEqualTo(12);
        }

        @Test
        @DisplayName("에이스를 11로 계산한다.")
        void cardSumWithACE_ELEVEN() {
            Participant participant = new Participant();
            Card card1 = new Card(Suit.HEART, Denomination.ACE);
            Card card2 = new Card(Suit.SPADE, Denomination.KING);

            participant.addCard(card1);
            participant.addCard(card2);
            int sum = participant.calculateDenominations();

            assertThat(sum).isEqualTo(21);
        }

        @Test
        @DisplayName("에이스를 1로 계산한다.")
        void cardSumWithACE_ONE() {
            Participant participant = new Participant();
            Card card1 = new Card(Suit.HEART, Denomination.TWO);
            Card card2 = new Card(Suit.SPADE, Denomination.TEN);
            Card card3 = new Card(Suit.CLUB, Denomination.ACE);

            participant.addCard(card1);
            participant.addCard(card2);
            participant.addCard(card3);
            int sum = participant.calculateDenominations();

            assertThat(sum).isEqualTo(13);
        }
    }
}
