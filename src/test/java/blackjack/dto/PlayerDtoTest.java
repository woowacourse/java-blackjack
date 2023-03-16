package blackjack.dto;

import blackjack.domain.user.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerDtoTest {

    @Test
    void 플레이어_DTO는_이름_점수_카드정보를_가진다() {
        String playerName = "dummy";

        Player player = new Player(playerName);
        PlayerDto dealerDto = new PlayerDto(player);

        assertAll(
                () -> Assertions.assertThat(dealerDto.getCards()).isEmpty(),
                () -> Assertions.assertThat(dealerDto.getName()).isEqualTo(playerName),
                () -> Assertions.assertThat(dealerDto.getName()).isEqualTo(playerName)
        );
    }
}
