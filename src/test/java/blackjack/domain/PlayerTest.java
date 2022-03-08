package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PlayerTest {

    @Test
    public void 참여자생성() {
        Player player = Player.of("test");
        assertThat(player.getName()).isEqualTo("test");
    }
}