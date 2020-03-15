package domain;

import domain.card.Cards;
import domain.player.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersInformationTest {

    @DisplayName("유저의 정보들을 담는 객체 생성")
    @Test
    void userInformationTest() {
        Cards cards = new Cards();
        List<String> playerName = new ArrayList<>(Arrays.asList("pobi", "subway"));

        Users users = new Users(cards,playerName);
        UsersInformation usersInformationTest = new UsersInformation(users);

        Assertions.assertThat(usersInformationTest.getUsersInformation())
                .extracting("name")
                .containsExactly("딜러","pobi", "subway");
    }

    @DisplayName("딜러정보를 생성하는지 테스트")
    @Test
    void dealerInformationTest() {
        Cards cards = new Cards();
        List<String> playerName = new ArrayList<>(Arrays.asList("pobi", "subway"));
        Users users = new Users(cards,playerName);
        UsersInformation usersInformation = new UsersInformation(users);

        Assertions.assertThat(usersInformation.getDealerInformation()).extracting("name").isEqualTo("딜러");
    }

    @DisplayName("플레이정보를 생성하는지 테스트")
    @Test
    void playerInformationTest() {
        Cards cards = new Cards();
        List<String> playerName = new ArrayList<>(Arrays.asList("pobi", "subway"));
        Users users = new Users(cards,playerName);
        UsersInformation usersInformation = new UsersInformation(users);

        Assertions.assertThat(usersInformation.getPlayerInformation())
                .extracting("name")
                .containsExactly("pobi", "subway");
    }
}
