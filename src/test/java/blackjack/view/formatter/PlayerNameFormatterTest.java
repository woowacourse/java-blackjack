package blackjack.view.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNameFormatterTest {

    @Test
    @DisplayName("플레이어의 이름을 형식에 맞게 포맷한다.")
    void formatWithCardComment() {
        assertThat(PlayerNameFormatter.formatWithCardComment(new Name("pobi"))).isEqualTo("pobi 카드: ");
    }

    @Test
    @DisplayName("플레이어의 이름을 형식에 맞게 포맷한다.")
    void format() {
        assertThat(PlayerNameFormatter.format(new Name("pobi"))).isEqualTo("pobi : ");
    }
}
