package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersTest {
    @DisplayName("이름별로 참여자들을 생성한다.")
    @Test
    void createUsers() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Users users = new Users(names);

        assertThat(users).isInstanceOf(Users.class);
    }

    @DisplayName("Users 일급 컬렉션을 반환한다.")
    @Test
    void users() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Users users = new Users(names);

        assertThat(users.users().size()).isEqualTo(3);
    }

    @DisplayName("각 사용자에게 초기에 카드 두장을 배분한다.")
    @Test
    void DistributeToEachUser() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Users users = new Users(names);
        users.distributeToEachUser();
        assertThat(users.users().stream().allMatch(user -> user.cards.cards().size() == 2)).isTrue();
    }

    @DisplayName("각 사용자들의 모든 카드를 보여준다.")
    @Test
    void showCardsByUsers(){
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Users users = new Users(names);
        users.distributeToEachUser();
        List<Cards> cardsGroup = users.showCardsByUsers();
        assertThat(cardsGroup.stream().allMatch(cards -> cards.cards().size() == 2)).isTrue();
    }

    @DisplayName("사용자 이름들을 확인한다.")
    @Test
    void showNames() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Users users = new Users(names);
        List<String> namesGroup = users.showNames();
        assertThat(namesGroup).isEqualTo(Arrays.asList("amazzi", "dani", "pobi"));
    }
}
