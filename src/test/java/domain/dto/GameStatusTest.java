package domain.dto;

import domain.Gamer;
import domain.Name;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStatusTest {
    @Test
    @DisplayName("List<GamerDto>로 생성한다.")
    void create() {
        Assertions.assertThatCode(() -> GameStatus.of(
                GamerDto.from(new Gamer(new Name("딜러"))),
                List.of(GamerDto.from(new Gamer(new Name("test")))
                ))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("List<GamerDto>를 반환한다.")
    void getGamerDtos() {
        GamerDto gamerDto = GamerDto.from(new Gamer(new Name("test")));
        GameStatus gameStatus = GameStatus.of(
                GamerDto.from(new Gamer(new Name("딜러"))),
                List.of(gamerDto));

        Assertions.assertThat(gameStatus.getGamerDtos())
                .isEqualTo(List.of(gamerDto));
    }

    @Test
    @DisplayName("이름이 들어오면 GamerDto를 반환한다.")
    void getGamerDtoFromName() {
        GamerDto gamerDto = GamerDto.from(new Gamer(new Name("test")));
        GameStatus gameStatus = GameStatus.of(
                GamerDto.from(new Gamer(new Name("딜러"))),
                List.of(gamerDto));

        Assertions.assertThat(gameStatus.getGamerDtoFromName("test"))
                .isEqualTo(gamerDto);
    }

    @Test
    @DisplayName("없는 이름이 들어오면 예외를 발생한다.")
    void getGamerDtoFromNameException() {
        GamerDto gamerDto = GamerDto.from(new Gamer(new Name("test")));
        GameStatus gameStatus = GameStatus.of(
                GamerDto.from(new Gamer(new Name("딜러"))),
                List.of(gamerDto));

        Assertions.assertThatThrownBy(() -> gameStatus.getGamerDtoFromName("wrongName"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 참여자 입니다.");
    }
}
