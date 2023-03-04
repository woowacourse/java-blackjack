package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    private Participant participant;

    @BeforeEach
    void setUp() {
        participant = new Participant(new ArrayList<>(List.of(new Card("2하트", 2), new Card("K다이아", 10))), "Leo");
    }

    @Test
    @DisplayName("참가자가 카드를 받는다.")
    void receiveCard() {
        participant.receiveCard(new Card("10클로버", 10));
        assertThat(participant.getCardNames().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("참가자의 handValue를 계산한다.")
    void getHandValue() {
        assertThat(participant.getHandValue()).isEqualTo(12);
    }

    @Test
    @DisplayName("딜러는 하나만 존재한다.")
    void equalsTest() {
        assertThat(new Dealer().equals(new Dealer())).isTrue();
    }

    @Test
    @DisplayName("같은 이름의 참가자는 하나만 존재한다.")
    void eqaulsTestWhenDifferentHand() {
        assertThat(new Participant(new ArrayList<>(List.of(new Card("2하트", 2), new Card("K다이아", 10))), "Leo").equals(
            new Participant(new ArrayList<>(List.of(new Card("4하트", 4), new Card("2다이아", 2))), "Leo"))).isTrue();
    }
}
