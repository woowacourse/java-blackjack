package domain;

import domain.player.PlayerInputInformation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerInputInformationTest {
    @DisplayName("플레이어 수만큼 입력한 배팅금액이 잘들어있는지 테스트")
    @Test
    void constructorTest() {
        Map<String,Double> playerName = new LinkedHashMap<>();
        playerName.put("pobi",3000d);
        playerName.put("subway",1000d);
        PlayerInputInformation playerInputInformation =
                new PlayerInputInformation(playerName);

        Map<String,Double> playerInformation = playerInputInformation.getPlayerInformation();

        Assertions.assertThat(playerInformation.keySet()).containsExactly("pobi","subway");
        Assertions.assertThat(playerInformation.values()).containsExactly(3000d,1000d);

    }
}
