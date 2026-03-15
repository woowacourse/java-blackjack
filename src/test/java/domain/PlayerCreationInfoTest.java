package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerCreationInfoTest {

    @Test
    void 플레이어_생성정보를_만들_수_있다(){
        Name name = Name.from("pobi");
        BettingMoney bettingMoney = BettingMoney.of(1000);
        PlayerCreationInfo playerCreationInfo = PlayerCreationInfo.of(name, bettingMoney);

        assertThat(playerCreationInfo.getName()).isEqualTo(name);
        assertThat(playerCreationInfo.getBettingMoney()).isEqualTo(bettingMoney);
    }
}
