package domain;

import domain.participant.BettingMoney;
import domain.participant.Name;
import domain.participant.PlayerInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerInfoTest {

    @Test
    void 플레이어_생성정보를_만들_수_있다() {
        Name name = Name.from("pobi");
        BettingMoney bettingMoney = BettingMoney.of(1000);
        PlayerInfo playerInfo = new PlayerInfo(name, bettingMoney);

        assertThat(playerInfo.name()).isEqualTo(name);
        assertThat(playerInfo.bettingMoney()).isEqualTo(bettingMoney);
    }
}
