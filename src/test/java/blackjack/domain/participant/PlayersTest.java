package blackjack.domain.participant;

import blackjack.domain.participant.attribute.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static blackjack.domain.participant.Players.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {
    private static final List<Name> names = Arrays.asList("포비", "쪼밀리", "타미").stream()
            .map(Name::new)
            .collect(Collectors.toList());

    @DisplayName("플레이어 이름 리스트로 Players 생성 확인")
    @Test
    void test1() {
        List<Player> expectedList = Arrays.asList(new Player("포비"), new Player("쪼밀리"),
                new Player("타미"));

        Players players = PlayersFactory.createPlayers(names);
        List<Player> actualList = players.getPlayers();

        assertThat(actualList).isEqualTo(expectedList);
    }

    @DisplayName("예외 테스트: 생성자에 null, empty, 5명 이상의 리스트가 전달된 경우 Exception 발생")
    @Test
    void test2() {
        assertThatThrownBy(() -> new Players(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ARGUMENT_ERR_MSG);

        assertThatThrownBy(() -> new Players(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(EMPTY_ARGUMENT_ERR_MSG);

        List<Name> names = Arrays.asList("포비", "쪼밀리", "타미", "제이슨", "CU", "워니", "준", "브라운").stream()
                .map(Name::new)
                .collect(Collectors.toList());
        assertThatThrownBy(() -> PlayersFactory.createPlayers(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MAX_PLAYER_ERR_MSG);
    }

    @DisplayName("플레이어들의 이름 확인")
    @Test
    void test3() {
        List<String> expectedNames = Arrays.asList("포비", "쪼밀리", "타미");
        List<Name> names = expectedNames.stream().map(Name::new).collect(Collectors.toList());

        Players players = PlayersFactory.createPlayers(names);


        List<String> actualNames = players.names();

        assertThat(actualNames).isEqualTo(expectedNames);
    }
}
