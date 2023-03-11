package domain.player;

import domain.player.info.PlayerInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {

    @Test
    @DisplayName("중복된 이름이 입력되면, 예외가 발생한다")
    void givenDuplicateName_thenFail() {
        final PlayerInfo playerInfo1 = new PlayerInfo.PlayerInfoBuilder("준팍")
                .setBetAmount(0)
                .build();
        final PlayerInfo playerInfo2 = new PlayerInfo.PlayerInfoBuilder("준팍")
                .setBetAmount(1000)
                .build();

        final List<Participant> participants = List.of(Participant.of(playerInfo1), Participant.of(playerInfo2));

        assertThatThrownBy(() -> Participants.of(participants))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되지 않은 이름만 입력해주세요");
    }
}
