package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import constant.CardNumber;
import constant.Emblem;

class ParticipantTest {
    @Nested
    @DisplayName("참가자 카드 뽑기")
    class PickCard {

        @DisplayName("참가자는 덱으로부터, 올바르게 카드를 뽑는다.")
        @Test
        public void pickCard() {
            // given
            final Participant participant = new MockParticipant();
            final List<Card> cards = List.of(new Card(CardNumber.ACE, Emblem.CLUB));
            final Deck deck = new Deck(new ArrayDeque<>(cards));

            // when
            participant.pickCard(deck);

            // then
            assertThat(participant.getHand().hand()).isNotEmpty();
        }
    }

    private static class MockParticipant extends Participant {

        @Override
        public boolean isPickCard() {
            return false;
        }
    }
}
