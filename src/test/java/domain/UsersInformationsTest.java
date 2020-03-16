package domain;

import domain.card.Cards;
import domain.player.Dealer;
import domain.player.Players;
import domain.player.UsersInformations;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersInformationsTest {

    @DisplayName("플레이정보를 생성하는지 테스트")
    @Test
    void playerInformationTest() {
        Cards cards = new Cards();
        List<String> playerName = new ArrayList<>(Arrays.asList("pobi", "subway"));
        Players players = new Players(cards,playerName);
        domain.player.UsersInformations usersInformations = new domain.player.UsersInformations(players);

        Assertions.assertThat(usersInformations.getPlayerInformation())
                .extracting("name")
                .containsExactly("pobi", "subway");
    }
}
