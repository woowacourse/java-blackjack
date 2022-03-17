package dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.player.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerInfoDtoTest {
    @Test
    @DisplayName("PlayerInfoDto 생성 테스트")
    void createPlayerInfoDto() {
        // given
        Dealer dealer = new Dealer();
        PlayerInfoDto dealerInfoDto = PlayerInfoDto.from(dealer);

        // when
        String name = dealerInfoDto.getName();
        boolean isDealer = dealerInfoDto.isDealer();

        // then
        assertAll(
                () -> assertThat(name).isEqualTo("딜러"),
                () -> assertThat(isDealer).isTrue()
        );
    }
}
