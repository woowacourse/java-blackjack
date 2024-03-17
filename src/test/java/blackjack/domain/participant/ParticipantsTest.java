package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import blackjack.domain.common.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        List<String> playerNames = List.of("아톰", "구름");
        List<Money> playersMoney = List.of(new Money(1000), new Money(2000));

        assertThatCode(() -> new Participants(playerNames, playersMoney))
                .doesNotThrowAnyException();
    }

    @DisplayName("참여자들은 딜러 포함 최소 2명 이상이어야 한다.")
    @Test
    void validatePlayerSize() {
        List<String> emptyPlayerNames = Collections.emptyList();
        List<Money> playersMoney = List.of(new Money(1000));

        assertThatThrownBy(() -> new Participants(emptyPlayerNames, playersMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참여자들은 중복된 이름을 가질 수 없다.")
    @Test
    void validateDuplicatedPlayerName() {
        List<String> duplicatedPlayerNames = List.of("atom", "atom");
        List<Money> playersMoney = List.of(new Money(1000));

        assertThatThrownBy(() -> new Participants(duplicatedPlayerNames, playersMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참여자들은 딜러와 이름이 같을 수 없다.")
    @Test
    void validateIncludeDealerName() {
        List<String> dealerNameIncludedPlayerNames = List.of("딜러");
        List<Money> playersMoney = List.of(new Money(1000));

        assertThatThrownBy(() -> new Participants(dealerNameIncludedPlayerNames, playersMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주어진 사용자 이름의 수와 사용자 돈의 수는 일치해야 한다.")
    @Test
    void validateSameSize() {
        List<String> playerNames = List.of("pobi", "tomi");
        List<Money> invalidSizePlayersMoney = List.of(new Money(1000));

        assertThatThrownBy(() -> new Participants(playerNames, invalidSizePlayersMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
