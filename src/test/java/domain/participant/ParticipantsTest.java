package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("성공: 참여자 포함 여부 반환")
    void doesNotContain() {
        Participants participants = Participants.from(List.of("yes"));
        Player player = participants.getPlayers()
            .get(0);
        Dealer dealer = participants.getDealer();
        Player foreigner = new Player(new Name("no"));

        assertThat(participants.doesNotContain(player)).isFalse();
        assertThat(participants.doesNotContain(dealer)).isFalse();
        assertThat(participants.doesNotContain(foreigner)).isTrue();
    }
}
