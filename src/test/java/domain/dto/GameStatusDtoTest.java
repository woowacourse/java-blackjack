package domain.dto;

import domain.Gamer;
import domain.Name;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStatusDtoTest {
    @Test
    @DisplayName("List<GamerDto>로 생성한다.")
    void create() {
        Assertions.assertThatCode(() -> GameStatusDto.of(List.of(
                GamerDto.from(new Gamer(new Name("test")))
        ))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("List<GamerDto>를 반환한다.")
    void getGamerDtos() {
        GamerDto gamerDto = GamerDto.from(new Gamer(new Name("test")));
        GameStatusDto gameStatusDto = GameStatusDto.of(List.of(gamerDto));

        Assertions.assertThat(gameStatusDto.getGamerDtos())
                .isEqualTo(List.of(gamerDto));
    }

    @Test
    @DisplayName("이름이 들어오면 GamerDto를 반환한다.")
    void getGamerDtoFromName() {
        GamerDto gamerDto = GamerDto.from(new Gamer(new Name("test")));
        GameStatusDto gameStatusDto = GameStatusDto.of(List.of(gamerDto));

        Assertions.assertThat(gameStatusDto.getGamerDtoFromName("test"))
                .isEqualTo(gamerDto);
    }

    @Test
    @DisplayName("없는 이름이 들어오면 예외를 발생한다.")
    void getGamerDtoFromNameException() {
        GamerDto gamerDto = GamerDto.from(new Gamer(new Name("test")));
        GameStatusDto gameStatusDto = GameStatusDto.of(List.of(gamerDto));

        Assertions.assertThatThrownBy(() -> gameStatusDto.getGamerDtoFromName("wrongName"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 참여자 입니다.");
    }
}
