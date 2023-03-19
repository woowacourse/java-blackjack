package domain.player;

import domain.player.info.ParticipantInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {

    @Test
    @DisplayName("중복된 이름이 입력되면, 예외가 발생한다")
    void givenDuplicateName_thenFail() {
        final ParticipantInfo participantInfo1 = new ParticipantInfo.ParticipantBuilder("준팍")
                .setBetAmount(1100)
                .build();
        final ParticipantInfo participantInfo2 = new ParticipantInfo.ParticipantBuilder("준팍")
                .setBetAmount(1000)
                .build();

        final List<Participant> participants = List.of(Participant.of(participantInfo1), Participant.of(participantInfo2));

        assertThatThrownBy(() -> Participants.of(participants))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되지 않은 이름만 입력해주세요");
    }
}
