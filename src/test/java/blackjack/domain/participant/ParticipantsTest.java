package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("참가자 중에서 플레이어를 추출한다.")
    void extractPlayerTest() {
        //given
        List<String> playerNames = List.of("ako", "maco");
        Participants participants = Participants.generate(playerNames, List.of("1000", "1000"));

        //when
        List<Player> result = participants.extractPlayers();

        //then
        assertThat(result.size()).isEqualTo(playerNames.size());
    }

    @Test
    @DisplayName("참가자 중에서 딜러를 추출한다.")
    void extractDealerTest() {
        //given
        List<String> playerNames = List.of("ako", "maco");
        Participants participants = Participants.generate(playerNames, List.of("1000", "1000"));

        //when
        Dealer result = participants.extractDealer();

        //then
        assertThat(result.getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("플레이어의 이름을 반환한다.")
    void getPlayersName() {
        //given
        List<String> playerNames = List.of("ako", "maco");
        Participants participants = Participants.generate(playerNames, List.of("1000", "1000"));
        //when
        List<String> participantsName = participants.getParticipantsName();

        //then
        assertThat(participantsName.containsAll(playerNames)).isTrue();
    }
}