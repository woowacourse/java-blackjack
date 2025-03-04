package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantRepositoryTest {

    @DisplayName("딜러의 객체를 기본적으로 가지고 있다")
    @Test
    void test1() {
        // given
        ParticipantRepository participantRepository = ParticipantRepository.getInstance();
        Participant expectedParticipant = new Participant("딜러", null);

        // when
        Participant participant = participantRepository.getByName("딜러");

        // then
        Assertions.assertThat(participant).isEqualTo(expectedParticipant);
    }
}
