package blackjack.domain.participants;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void 본인의_이름을_반환한다() {
        // given
        String playerNameValue = "player";
        Name playerName = new Name(playerNameValue);
        // when
        Player player = Player.createEmptyHand(playerName, Bet.zero());
        // then
        assertThat(player.getName()).isEqualTo(playerNameValue);
    }
}
