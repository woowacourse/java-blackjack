package blackjack.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    @DisplayName("참가자 중 플레이어는 중복인 이름을 허용하지 않는다.")
    @Test
    void should_ThrowException_When_PlayerNameDuplicated() {
        List<String> playerNames = List.of("pobi", "pobi", "jason");

        Assertions.assertThatThrownBy(() -> Participants.of(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 중복될 수 없습니다.");
    }

}
