package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ParticipantsTest {
    @Test
    void playersTest() {
        assertThatCode(() -> Participants.from(List.of("leo", "reo", "reoleo")))
            .doesNotThrowAnyException();
    }
}
