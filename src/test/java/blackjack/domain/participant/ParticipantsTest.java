package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ParticipantsTest {
    @Test
    @DisplayName("참가자의 이름으로 참가자를 찾는다.")
    public void findParticipantsByNameTest() {
        Participants participants = new Participants();
        participants.addUsers(new String[]{"aaa", "bbb", "ccc"});

        assertDoesNotThrow(() -> {
            participants.getUserByName("aaa");
        });
    }

    @Test
    @DisplayName("참가자의 이름으로 참가자를 찾지 못하면 예외를 발생시킨다.")
    public void findParticipantsByNameNotIncludeTest() {
        Participants participants = new Participants();
        participants.addUsers(new String[]{"aaa", "bbb", "ccc"});

        assertThatThrownBy(() -> {
            participants.getUserByName("zzz");
        }).isInstanceOf(RuntimeException.class);
    }

}
