package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("딜러인지 확인할 수 있다.")
    void canCheckDealer() {
        // given
        Nickname nickname = Nickname.createDealerNickname();
        Player player = new Player(nickname);

        // when
        boolean isDealer = player.isDealer();

        // then
        assertThat(isDealer).isEqualTo(true);
    }

}