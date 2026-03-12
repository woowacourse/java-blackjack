package domain.participant;

import static exception.ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Hand;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("플레이어 참가 제한에 맞아야 한다.")
    public void 플레이어_수가_제한_범위_이내라면_성공() {
        // given
        final List<Participant> players = new ArrayList<>();
        for (int i = 1; i <= Participants.MAXIMUM_BOUND; i++) {
            players.add(new Participant(new Name(String.format("포비%d", i)), new Hand()));
        }

        // then
        assertThat(new Participants(players))
                .isInstanceOf(Participants.class);
    }

    @Test
    @DisplayName("플레이어 참가 수는 범위가 제한되어 있다.")
    public void 플레이어_수가_제한_범위를_초과하면_실패() {
        // given
        final List<Participant> players = new ArrayList<>();
        for (int i = 1; i <= Participants.MAXIMUM_BOUND + 1; i++) {
            players.add(new Participant(new Name(String.format("포비%d", i)), new Hand()));
        }

        // then
        assertThatThrownBy(() -> new Participants(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PLAYER_COUNT_OUT_OF_RANGE.getMessage());
    }
}
