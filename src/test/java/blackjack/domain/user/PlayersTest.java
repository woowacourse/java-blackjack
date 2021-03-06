package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @DisplayName("이름별로 참여자들을 생성한다.")
    @Test
    void createUsers() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = Players.of(names);

        assertThat(players).isInstanceOf(Players.class);
    }

    @DisplayName("Users 일급 컬렉션을 반환한다.")
    @Test
    void users() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = Players.of(names);

        assertThat(players.players().size()).isEqualTo(3);
    }

    @DisplayName("각 사용자에게 초기에 카드 두장을 배분한다.")
    @Test
    void DistributeToEachUser() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = Players.of(names);
        players.distributeToEachUser();
        assertThat(players.players().stream().allMatch(user -> user.cards.cards().size() == 2)).isTrue();
    }

    @DisplayName("각 사용자들의 모든 카드를 보여준다.")
    @Test
    void showCardsByUsers() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = Players.of(names);
        players.distributeToEachUser();
        List<Cards> cardsGroup = players.showCardsByUsers();
        assertThat(cardsGroup.stream().allMatch(cards -> cards.cards().size() == 2)).isTrue();
    }
}
