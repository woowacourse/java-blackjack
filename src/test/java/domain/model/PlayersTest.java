package domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.vo.Bet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("중복된 원소가 있는 문자열 리스트로 플레이어 생성 실패 테스트")
    public void testCreatByFromWhenDuplicateNameExist() {
        //given
        final List<String> names = List.of("a", "a", "b");
        final List<Double> battings = List.of(1000D, 2000D, 3000D);

        //when
        //then
        assertThatThrownBy(() -> Players.from(names, battings))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 원소가 없는 문자열 리스트로 플레이어 생성 성공 테스트")
    public void testCreateSuccess() {
        //given
        final List<String> names = List.of("a", "b");
        final List<Double> battings = List.of(1000D, 2000D);

        //when
        //then
        assertDoesNotThrow(() -> Players.from(names, battings));
    }

    @Test
    @DisplayName("크기 반환 테스트")
    public void testSize() {
        //given
        final int size = 10;
        final List<String> names = new ArrayList<>();
        final List<Double> battings = new ArrayList<>();
        IntStream.range(0, size)
            .forEach(i -> {
                names.add("test" + i);
                battings.add((double) (i * 1000));
            });
        Players players = Players.from(names, battings);

        //when
        int result = players.count();

        //then
        assertThat(result).isEqualTo(size);
    }

    @Test
    @DisplayName("원소 수정 테스트")
    public void testSet() {
        //given
        Players players = Players.from(List.of("test1", "test2", "test3"), List.of(1000D, 2000D, 3000D));
        Player player = new Player(Cards.makeEmpty(), "player", Bet.of(1D));

        //when
        players.set(1, player);

        //then
        assertThat(player.getName()).isEqualTo(players.get(1).getName());
        assertThat(player.getBet().getValue()).isEqualTo(1D);
    }
}
