package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    void 구분자를_기준으로_전체_플레이어_리스트를_생성한다() {
        // given
        String rawPlayerNames = "pobi,jason";
        // when
        Participants participants = Participants.from(rawPlayerNames);
        // then
        List<String> actualPlayerNames = participants.getPlayers().stream().map(Participant::getName).toList();
        assertThat(actualPlayerNames).contains("pobi", "jason");
    }

    @Test
    void 플레이어_이름이_중복되면_에러를_던진다() {
        // given
        String duplicatedRawPlayerNames = "pobi,pobi";
        // when
        assertThatThrownBy(() -> Participants.from(duplicatedRawPlayerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
