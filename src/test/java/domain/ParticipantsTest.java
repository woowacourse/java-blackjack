package domain;

import domain.user.Participant;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

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

    @DisplayName("현재 게임을 진행해야하는 플레이어를 준다.")
    @TestFactory
    Stream<DynamicTest> getCurrentParticipant() {
        Participant firstPlayer = new Participant("firstPlayer");
        Participant secondPlayer = new Participant("secondPlayer");
        Participants participants = new Participants(List.of(firstPlayer, secondPlayer));
        return Stream.of(DynamicTest.dynamicTest("처음에는 첫 참가자를 반환한다.",
                () -> Assertions.assertThat(participants.getCurrentParticipant()).isEqualTo(firstPlayer)),
            DynamicTest.dynamicTest("앞에 참가자가 카드를 더 이상 못 뽑을 경우 다음 참가자를 반환한다.", () -> {
                firstPlayer.addCard(new Card(CardNumber.JACK, CardShape.SPADE));
                firstPlayer.addCard(new Card(CardNumber.QUEEN, CardShape.HEART));
                firstPlayer.addCard(new Card(CardNumber.ACE, CardShape.DIAMOND));
                participants.update(firstPlayer);
                Assertions.assertThat(participants.getCurrentParticipant()).isEqualTo(secondPlayer);
            })
        );
    }
}
