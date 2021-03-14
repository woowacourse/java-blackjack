package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamesTest {

    @Test
    @DisplayName("플레이어들의 이름 생성 테스트")
    void testCreatePlayerNames() {
        List<String> nameList = Arrays.asList("미립", "현구막", "포비");
        assertThat(new Names(nameList))
            .isInstanceOf(Names.class);
    }

    @Test
    @DisplayName("플레이어들의 이름 중복 테스트")
    void testDuplicateException() {
        List<String> nameList = Arrays.asList("미립", "현구막", "미립");
        assertThatThrownBy(() -> new Names(nameList)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("모든 플레이어 이름들이 동작을 완료했는지 확인")
    void testAllPlayersDone() {
        List<String> nameList = Arrays.asList("미립", "현구막", "포비");
        Names names = new Names(nameList);

        names.passTurnToNext();
        assertThat(names.isDoneAllPlayers()).isFalse();
        names.passTurnToNext();
        assertThat(names.isDoneAllPlayers()).isFalse();
        names.passTurnToNext();
        assertThat(names.isDoneAllPlayers()).isTrue();
    }

    @Test
    @DisplayName("현재 순서의 플레이어 이름이 반환되는지 테스트")
    void testCurrentPlayerName() {
        List<String> nameList = Arrays.asList("미립", "현구막", "포비");
        Names names = new Names(nameList);

        assertThat(names.getCurrentTurnNameValue()).isEqualTo("미립");
        names.passTurnToNext();
        assertThat(names.getCurrentTurnNameValue()).isEqualTo("현구막");
        names.passTurnToNext();
        assertThat(names.getCurrentTurnNameValue()).isEqualTo("포비");
    }
}