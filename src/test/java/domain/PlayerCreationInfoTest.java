package domain;

import domain.participant.BettingMoney;
import domain.participant.Name;
import dto.PlayerCreationInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerCreationInfoTest {

    @Test
    void 플레이어_생성정보를_만들_수_있다(){
        Name name = Name.from("pobi");
        BettingMoney bettingMoney = BettingMoney.of(1000);
        PlayerCreationInfo playerCreationInfo = new PlayerCreationInfo(name, bettingMoney);

        assertThat(playerCreationInfo.name()).isEqualTo(name);
        assertThat(playerCreationInfo.bettingMoney()).isEqualTo(bettingMoney);
    }
}
