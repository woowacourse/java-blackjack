package model.participant;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ParticipantTest {
    @Test
    public void 이름_출력_정상_작동() {
        Participant participant = new Participant(new PlayerName("jason"));

        assertThat(participant.getName()).isEqualTo("jason");
    }
}
