package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsTest {
    @DisplayName("이름별로 참여자들을 생성한다.")
    @Test
    void createParticipants() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Participants participants = new Participants(names);

        assertThat(participants).isInstanceOf(Participants.class);
    }
}
