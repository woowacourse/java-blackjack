package domain;

import domain.player.PlayerInputInformation;
import domain.player.PlayersName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

public class PlayerInputInformationTest {
    @DisplayName("플레이어 수만큼 입력한 배팅금액이 잘들어있는지 테스트")
    @Test
    void constructorTest() {
        PlayersName playersName = new PlayersName("pobi,subway");
        PlayerInputInformation playerInputInformation =
                new PlayerInputInformation(playersName.getPlayerName(), Arrays.asList(3000d,1000d));

        Map<String,Double> playerInformation = playerInputInformation.getPlayerInformation();

        Assertions.assertThat(playerInformation.keySet()).containsExactly("pobi","subway");
        Assertions.assertThat(playerInformation.values()).containsExactly(3000d,1000d);

    }
}
