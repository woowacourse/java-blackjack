package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    private Participant participant;

    @BeforeEach
    void setUp() {
        participant = new Participant(new ArrayList<>(List.of(new Card("2하트", 2), new Card("K다이아", 10))), "Leo");
    }

    @Test
    void receiveCard() {
        participant.receiveCard(new Card("10클로버", 10));
        assertThat(participant.getCardNames().size()).isEqualTo(3);
    }

    @Test
    void getHandValue() {
        assertThat(participant.getHandValue()).isEqualTo(12);
    }

    @Test
    void equalsTest() {
        assertThat(new Dealer().equals(new Dealer())).isTrue();
    }

    @Test
    void eqaulsTestWhenDifferentHand() {
        assertThat(new Participant(new ArrayList<>(List.of(new Card("2하트", 2), new Card("K다이아", 10))), "Leo").equals(
            new Participant(new ArrayList<>(List.of(new Card("4하트", 4), new Card("2다이아", 2))), "Leo"))).isTrue();
    }
}
