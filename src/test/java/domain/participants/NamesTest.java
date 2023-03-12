package domain.participants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.message.ExceptionMessage;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamesTest {

    @Test
    @DisplayName("플레이어들의 이름 생성이 성공한다.")
    void create_success() {
        // given
        List<String> names = List.of("춘식", "제이");

        // when & then
        Assertions.assertDoesNotThrow(
                () -> Names.createNames(names)
        );
    }

    @Test
    @DisplayName("참여 플레이어의 수가 1명 미만이면 예외가 발생한다.")
    void throws_exception_when_participants_size_under_minimum() {
        // given
        List<String> names = List.of();

        // when & then
        assertThatThrownBy(() -> Names.createNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.PLAYER_INVALID_NUMBERS.getMessage());
    }

    @Test
    @DisplayName("참여 플레이어의 수가 4명 초과이면 예외가 발생한다.")
    void throws_exception_when_participants_size_over() {
        // given
        List<String> names = List.of("포비", "춘식", "제이미", "제이", "우르");

        // when & then
        assertThatThrownBy(() -> Names.createNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.PLAYER_INVALID_NUMBERS.getMessage());
    }

    @Test
    @DisplayName("참여 플레이어의 이름이 중복이 되면 예외가 발생한다.")
    void throws_exception_when_participant_names_duplicated() {
        // given
        List<String> names = List.of("춘식", "춘식");

        // when & then
        assertThatThrownBy(() -> Names.createNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.PLAYER_NAME_NOT_DUPLICATED.getMessage());
    }

    @Test
    @DisplayName("플레이어의 이름에 딜러가 들어갈 수 없다.")
    void throws_exception_when_participant_names_contain_dealer() {
        // given
        List<String> names = List.of("춘식", "딜러");

        // when & then
        assertThatThrownBy(() -> Names.createNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.PLAYER_NAME_NOT_DEALER.getMessage());
    }
}
