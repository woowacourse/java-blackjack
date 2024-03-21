package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNamesTest {

    @DisplayName("플레이어가 없으면 예외가 발생한다.")
    @Test
    void testCreateWithEmptyEntry() {
        assertThatThrownBy(() -> new PlayerNames(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복되는 플레이어 이름이 존재하면 예외가 발생한다.")
    @Test
    void testCreateWithDuplicateName() {
        PlayerName playerName1 = new PlayerName("pobi");
        PlayerName playerName2 = new PlayerName("pobi");

        assertThatThrownBy(() -> new PlayerNames(List.of(playerName1, playerName2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("PlayerNames를 생성한다.")
    @Test
    void testCreateWithValidNames() {
        PlayerName pobi = new PlayerName("pobi");
        PlayerName jason = new PlayerName("jason");

        assertThatCode(() -> new PlayerNames(List.of(pobi, jason)))
                .doesNotThrowAnyException();
    }

    @DisplayName("주어진 플레이어 이름 문자열 리스트로 PlayerNames를 생성한다.")
    @Test
    void testCreateWithRawNames() {
        // given
        List<String> rawNames = List.of("pobi", "jason");

        // when
        PlayerNames playerNames = PlayerNames.create(rawNames);

        // then
        assertThat(playerNames.names()).containsExactly(
                new PlayerName("pobi"),
                new PlayerName("jason")
        );
    }
}
