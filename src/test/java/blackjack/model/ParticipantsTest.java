package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {"pobi,pobi", "pobi, pobi"})
    void 플레이어_이름이_중복되면_에러를_던진다(String duplicatedRawPlayerNames) {
        assertThatThrownBy(() -> Participants.from(duplicatedRawPlayerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
