package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {

    private final AceAdjustPolicy aceAdjustPolicy = new AceAdjustPolicy(new BustPolicyImpl());

    @Test
    void 구분자를_기준으로_전체_플레이어_리스트를_생성한다() {
        // given
        String rawPlayerNames = "pobi,jason";

        // when
        Players players = Players.from(rawPlayerNames, aceAdjustPolicy);

        // then
        List<String> actualPlayerNames = players.getPlayers().stream().map(Player::getName).toList();
        assertThat(actualPlayerNames).contains("pobi", "jason");
    }

    @Test
    void 플레이어_이름이_중복되면_에러를_던진다() {
        // given
        String duplicatedRawPlayerNames = "pobi,pobi";

        // when
        assertThatThrownBy(() -> Players.from(duplicatedRawPlayerNames, aceAdjustPolicy))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
