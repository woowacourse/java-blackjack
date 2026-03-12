package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.card.Hand;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private final Hand emptyHand = new Hand();

    @Test
    void 본인의_이름을_반환한다() {
        // given
        String playerName = "Player Name";
        // when
        Player player = new Player(playerName, emptyHand);
        // then
        assertThat(player.getName()).isEqualTo(playerName);
    }

    @Test
    void 이름이_null인_경우_에러를_던진다() {
        // given
        String playerName = null;
        // when & then
        assertThatThrownBy(() -> new Player(playerName, emptyHand))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이름이_blank인_경우_에러를_던진다() {
        // given
        String playerName = "";
        // when & then
        assertThatThrownBy(() -> new Player(playerName, emptyHand))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
