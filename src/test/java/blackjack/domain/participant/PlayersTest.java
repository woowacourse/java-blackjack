package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Figure;
import blackjack.domain.card.Type;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.ResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.participant.Name.EMPTY_NAME_ERR_MSG;
import static blackjack.domain.participant.Name.NULL_NAME_ERR_MSG;
import static blackjack.domain.participant.Players.MAX_PLAYER_ERR_MSG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @DisplayName("플레이어 이름 리스트로 Players 생성 확인")
    @Test
    void test1() {
        List<String> names = Arrays.asList("포비", "쪼밀리", "타미");

        List<Player> expectedList = Arrays.asList(new Player("포비"), new Player("쪼밀리"),
                new Player("타미"));

        Players players = new Players(names);
        List<Player> actualList = players.getPlayers();

        assertThat(actualList).isEqualTo(expectedList);
    }

    @DisplayName("예외 테스트: 생성자에 null, empty, 5명 이상의 리스트가 전달된 경우 Exception 발생")
    @Test
    void test2() {
        assertThatThrownBy(() -> new Players(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_NAME_ERR_MSG);

        assertThatThrownBy(() -> new Players(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(EMPTY_NAME_ERR_MSG);

        List<String> names = Arrays.asList("포비", "쪼밀리", "타미", "제이슨", "CU", "워니", "준", "브라운");
        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MAX_PLAYER_ERR_MSG);
    }

    @DisplayName("플레이어들의 이름 확인")
    @Test
    void test3() {
        List<String> names = Arrays.asList("포비", "쪼밀리", "타미");
        Players players = new Players(names);

        List<String> actualNames = players.names();

        assertThat(actualNames).isEqualTo(names);
    }

    @DisplayName("플레이어들의 결과 생성 확인")
    @Test
    void test4() {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(Type.FIVE, Figure.HEART));

        Players players = new Players(Arrays.asList("포비", "쪼밀리", "타미"));
        List<Player> playersList = players.getPlayers();
        playersList.get(0).addCard(Card.of(Type.FOUR, Figure.SPADE));
        playersList.get(1).addCard(Card.of(Type.FIVE, Figure.SPADE));
        playersList.get(2).addCard(Card.of(Type.SIX, Figure.SPADE));

        List<PlayerResult> expectedResult = Arrays.asList(
                new PlayerResult(new Name("포비"), ResultType.LOSE),
                new PlayerResult(new Name("쪼밀리"), ResultType.DRAW),
                new PlayerResult(new Name("타미"), ResultType.WIN));

        List<PlayerResult> actualResult = players.createPlayerResults(dealer);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
