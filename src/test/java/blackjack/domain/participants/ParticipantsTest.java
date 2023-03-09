package blackjack.domain.participants;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    private final String dealerName = "딜러";

    @DisplayName("플레이어의 이름 간 중복을 허용하지 않는다.")
    @Test
    void should_ThrowException_When_PlayerNamesDuplicated() {
        final List<String> playerNames = List.of("pobi", "pobi", "jason");

        Assertions.assertThatThrownBy(() -> Participants.of(dealerName, playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("플레이어의 이름과 딜러의 이름 간 중복을 허용하지 않는다.")
    @Test
    void should_ThrowException_When_PlayerNameDuplicatedWithDealerName() {
        final List<String> playerNames = List.of("pobi", "딜러");

        Assertions.assertThatThrownBy(() -> Participants.of(dealerName, playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 딜러 이름(" + dealerName + ")과 중복될 수 없습니다.");
    }
}
