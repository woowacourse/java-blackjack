package blackjack.domain.participants;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class PlayerTest {
    private final Bet bet = new Bet(1000L);

    @Test
    void 본인의_이름을_반환한다() {
        // given
        String playerNameValue = "player";
        Name playerName = new Name(playerNameValue);
        // when
        Player player = Player.createEmptyHand(playerName, bet);
        // then
        assertThat(player.getName()).isEqualTo(playerNameValue);
    }
}
