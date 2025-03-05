package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantRepositoryTest {

    private ParticipantRepository participantRepository = ParticipantRepository.getInstance();

    @DisplayName("딜러의 객체를 기본적으로 가지고 있다")
    @Test
    void test1() {
        // given
        Participant expectedParticipant = new Participant("딜러", null, Role.DEALER);

        // when
        Participant participant = participantRepository.getByName("딜러");

        // then
        assertThat(participant).isEqualTo(expectedParticipant);
    }

    @DisplayName("참여자 전원의 객체를 저장한다")
    @Test
    void test2() {
        //given
        List<Participant> participants = List.of(
                new Participant("mimi", Cards.createEmpty(), Role.PLAYER),
                new Participant("wade", Cards.createEmpty(), Role.PLAYER)
        );

        // when
        participantRepository.addAll(participants);

        // then
        assertThat(participantRepository.getAll()).containsAll(participants);
    }

    @DisplayName("딜러 혹은 참여자 이름을 통해 참여자를 조회한다")
    @Test
    void test3() {
        //given
        participantRepository.addAll(List.of(new Participant("mimi", Cards.createEmpty(), Role.PLAYER)));

        //when
        Participant participant = participantRepository.getByName("mimi");

        //then
        assertThat(participant).isEqualTo(new Participant("mimi", Cards.createEmpty(), Role.PLAYER));
    }

    @DisplayName("딜러를 제외한 모든 참여자를 조회한다")
    @Test
    void test4() {
        //given
        List<Participant> testParticipants = List.of(
                new Participant("테스트딜러", Cards.createEmpty(), Role.DEALER),
                new Participant("mimi", Cards.createEmpty(), Role.PLAYER),
                new Participant("wade", Cards.createEmpty(), Role.PLAYER));
        List<Participant> expectedParticipants = List.of(
                new Participant("mimi", Cards.createEmpty(), Role.PLAYER),
                new Participant("wade", Cards.createEmpty(), Role.PLAYER));
        participantRepository.addAll(testParticipants);

        //when
        List<Participant> participants = participantRepository.getAllPlayer();

        //then
        assertThat(participants).containsExactlyInAnyOrderElementsOf(
                expectedParticipants
        );
    }
}
