package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class PlayerTest {

    final Hand hand = new Hand();

    @Test
    void 본인의_이름을_반환한다() {
        // given
        Name playerName = new Name("Player Name");

        // when
        Player player = new Player(playerName, hand);

        // then
        assertThat(player.getName()).isEqualTo(playerName.get());
    }
}
