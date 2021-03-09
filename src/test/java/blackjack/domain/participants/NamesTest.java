package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NamesTest {
    @Test
    @DisplayName("중복된 이름이 있는지 확인")
    void validateSameName() {
        assertThatThrownBy(() -> new Names("j.on", "j.on"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("딜러를 제외한 플레이어 수가 1명 이상 7명 이하인지 확인")
    void validatePeopleNumber() {
        assertThatThrownBy(Names::new)
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Names("1", "2", "3", "4", "5", "6", "7", "8"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
