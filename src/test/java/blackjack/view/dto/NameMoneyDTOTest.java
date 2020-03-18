package blackjack.view.dto;

import blackjack.domain.generic.BettingMoney;
import blackjack.domain.player.PlayerInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NameMoneyDTOTest {

    @DisplayName("받은 이름과 베팅금액으로 플레이어 만들기")
    @Test
    void toEntity() {
        //given
        NameMoneyDTO nameMoneyDTO = new NameMoneyDTO("bebop", 1000);

        //when
        PlayerInfo playerInfo = nameMoneyDTO.toEntity();

        //then
        assertThat(playerInfo.getName()).isEqualTo("bebop");
        assertThat(playerInfo.getBettingMoney()).isEqualTo(BettingMoney.of(1000));
    }
}