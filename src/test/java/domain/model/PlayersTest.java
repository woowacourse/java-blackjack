package domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("중복된 원소가 있는 문자열 리스트로 플레이어 생성 실패 테스트")
    public void testCreatByFromWhenDuplicateNameExist() {
        //given
        final List<String> names = List.of("a", "a", "b");

        //when
        //then
        assertThatThrownBy(() -> Players.from(names))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 원소가 없는 문자열 리스트로 플레이어 생성 성공 테스트")
    public void testCreateSuccess() {
        //given
        final List<String> names = List.of("a", "b");

        //when
        //then
        assertDoesNotThrow(() -> Players.from(names));
    }

    @Test
    @DisplayName("크기 반환 테스트")
    public void testSize() {
        //given
        final int size = 10;
        List<String> names = IntStream.range(0, size)
            .mapToObj(i -> "test" + i)
            .collect(Collectors.toList());
        Players players = Players.from(names);

        //when
        int result = players.size();

        //then
        assertThat(result).isEqualTo(size);
    }

    @Test
    @DisplayName("원소 수정 테스트")
    public void testSet() {
        //given
        Players players = Players.from(List.of("test1", "test2", "test3"));
        Player player = new Player(Cards.makeEmpty(), "player");

        //when
        players.set(1, player);

        //then
        assertThat(player.getName()).isEqualTo(players.get(1).getName());
    }
}
