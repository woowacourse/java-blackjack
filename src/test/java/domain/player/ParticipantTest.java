package domain.player;

import org.junit.jupiter.api.BeforeEach;

class ParticipantTest {
    Participant participant;

    @BeforeEach
    void setUp() {
        participant = new Participant("test");
    }
}