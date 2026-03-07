package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    static final int ADJUST_VALUE = 10;
    final Hand hand = new Hand(new AceAdjustPolicy(ADJUST_VALUE, new BustPolicyImpl()));

    @Test
    void 본인의_이름을_반환한다() {
        // given
        String playerName = "Player Name";

        // when
        Player player = new Player(playerName, hand);

        // then
        assertThat(player.getName()).isEqualTo(playerName);
    }

    @Test
    void 이름이_null인_경우_에러를_던진다() {
        // given
        String playerName = null;

        // when & then
        assertThatThrownBy(() -> new Player(playerName, hand))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이름이_blank인_경우_에러를_던진다() {
        // given
        String playerName = "";

        // when & then
        assertThatThrownBy(() -> new Player(playerName, hand))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
