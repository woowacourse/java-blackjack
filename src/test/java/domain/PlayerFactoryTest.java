package domain;

import domain.card.Cards;
import domain.player.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerFactoryTest {
    @DisplayName("팩토리에서 생성한 Player 의 유효성 검증")
    @Test
    void createPlayersTest() {
        Cards cards = new Cards();
        List<String> playerName = new ArrayList<>(Arrays.asList("pobi","json"));
        PlayerFactory playerFactory = PlayerFactory.getInstance();

        Assertions.assertThat(playerFactory.createPlayers(cards, playerName)).isInstanceOf(Players.class);
    }
}
