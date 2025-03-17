package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BatMoniesTest {

    @Test
    @DisplayName("참여자의 이름을 토대로 배팅 정보를 가져온다.")
    void should_return_batMoney_by_player_name() {
        // given
        String playerName = "a";
        int money = 10000;
        BatMoney batMoney = new BatMoney(playerName, money);
        BatMonies batMonies = new BatMonies(List.of(batMoney));

        // when
        BatMoney foundBatMoney = batMonies.findByPlayerName(playerName);

        // then
        assertThat(foundBatMoney).isEqualTo(batMoney);
    }
}
