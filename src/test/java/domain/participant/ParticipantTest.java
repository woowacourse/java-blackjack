package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("요청한 카드 저장 확인 테스트")
    void shouldSuccessTakeCard() {
        Participant participant = new Player(new Name("seongha"), new HandCards(Collections.emptyList()));
        Card card1 = new Card(Suit.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Suit.DIAMOND, Denomination.THREE);

        participant.takeCard(card1);
        participant.takeCard(card2);
        List<Card> handCards = participant.getHandCards();

        assertThat(handCards.size()).isEqualTo(2);
        assertThat(handCards).contains(card1, card2);
    }

    @Test
    @DisplayName("Ace가 포함된 경우 1, 11 중 최적의 값을 선택해서 합을 구한다.")
    void getOptimalSumValueWhenAceContain() {
        Participant participant = new Player(new Name("seongha"), new HandCards(Collections.emptyList()));
        Card card1 = new Card(Suit.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Suit.DIAMOND, Denomination.ACE);
        Card card3 = new Card(Suit.DIAMOND, Denomination.THREE);

        participant.takeCard(card1);
        participant.takeCard(card2);
        participant.takeCard(card3);

        assertThat(participant.getOptimalCardValueSum()).isEqualTo(14);
        assertThat(participant.getOptimalCardValueSum()).isNotEqualTo(24);
    }

    @Test
    @DisplayName("Ace가 포함되지 않으면 단순 합을 구한다.")
    void getOptimalSumValueWhenAceNotContain() {
        Participant participant = new Player(new Name("seongha"), new HandCards(Collections.emptyList()));
        Card card1 = new Card(Suit.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Suit.DIAMOND, Denomination.NINE);
        Card card3 = new Card(Suit.DIAMOND, Denomination.FIVE);

        participant.takeCard(card1);
        participant.takeCard(card2);
        participant.takeCard(card3);

        assertThat(participant.getOptimalCardValueSum()).isEqualTo(24);
    }
}
