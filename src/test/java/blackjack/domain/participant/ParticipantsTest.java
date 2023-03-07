package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ParticipantsTest {

    @Test
    @DisplayName("플레이어 이름이 중복될 시 예외 처리 테스트")
    void validatePlayerNameDuplicateTest() {
        List<String> playerNames = List.of("IO", "IO");

        assertThatThrownBy(() -> new Participants(playerNames)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("딜러와 플레이어를 합쳐 participant 리스트로 반환 테스트")
    void getAllParticipantsTest() {
        String playerName1 = "IO";
        String playerName2 = "Bada";
        Participants participants = new Participants(List.of(playerName1, playerName2));

        List<Participant> allParticipants = participants.getAllParticipants();

        assertThat(allParticipants.get(0).getClass()).isEqualTo(Dealer.class);
        assertThat(allParticipants.get(1).getName()).isEqualTo(playerName1);
        assertThat(allParticipants.get(2).getName()).isEqualTo(playerName2);
    }
}