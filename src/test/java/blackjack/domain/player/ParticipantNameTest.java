package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("참가자 이름 테스트")
class ParticipantNameTest {

    @DisplayName("이름은 빈 문자열일 수 없다")
    @Test
    void testCreatePlayerNameWithEmpty() {
        assertThatThrownBy(() -> new PlayerName(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름이 빈 문자열입니다.");
    }
}
