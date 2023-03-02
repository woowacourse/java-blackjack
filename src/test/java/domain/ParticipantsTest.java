package domain;

import domain.participant.Participant;
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

    @DisplayName("참가자의 게임 상태 업데이트 기능 구현")
    @Test
    void updateTest() {
        Participant participant = new Participant("echo");
        Participants participants = new Participants(List.of(participant, new Participant("split")));
        participant.addCard(new Card(CardNumber.JACK, CardShape.SPADE));
        participant.addCard(new Card(CardNumber.JACK, CardShape.SPADE));
        participant.addCard(new Card(CardNumber.JACK, CardShape.SPADE));
        participants.update(participant);
        GameStatus gameStatus = participants.getGameStatusByParticipant(participant);
        Assertions.assertThat(gameStatus)
            .extracting("participantStatus")
            .isEqualTo(ParticipantStatus.BUST);
        Assertions.assertThat(gameStatus)
            .extracting("score")
            .isEqualTo(30);
    }
}
