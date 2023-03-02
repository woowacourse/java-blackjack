package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("참자자들 생성 테스트")
    @Test
    void create() {
        Participants participants = new Participants(List.of(new Participant("echo"), new Participant("split")));
        Assertions.assertThat(participants)
            .extracting("participantStatuses")
            .asInstanceOf(InstanceOfAssertFactories.map(Participant.class, GameStatus.class))
            .containsKeys(new Participant("echo"), new Participant("split"));
    }
}
