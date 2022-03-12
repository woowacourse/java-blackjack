package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

class ParticipantTest {

    @Test
    @DisplayName("참여자가 초기 카드 2장을 받는다")
    void receiveInitCard() {
        Participant participant = new Participant("president");

        participant.initCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.HEART, Denomination.THREE)));

        assertThat(participant.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("참여자가 카드를 추가로 받는다")
    void receiveCard() {
        Participant participant = new Participant("president");

        participant.initCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.HEART, Denomination.THREE)));

        participant.addCard(new Card(Suit.DIAMOND, Denomination.TWO));

        assertThat(participant.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("참여자는 버스트이다")
    void isBurst() {
        Participant participant = new Participant("president");

        participant.initCards(List.of(new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.HEART, Denomination.JACK)));

        participant.addCard(new Card(Suit.DIAMOND, Denomination.FIVE));

        assertThat(participant.isBurst()).isTrue();
    }

    @Test
    @DisplayName("참여자는 버스트가 아니다")
    void isNotBurst() {
        Participant participant = new Participant("president");

        participant.initCards(List.of(new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.HEART, Denomination.JACK)));

        assertThat(participant.isBurst()).isFalse();
    }
}