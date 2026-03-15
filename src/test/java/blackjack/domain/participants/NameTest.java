package blackjack.domain.participants;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class NameTest {
    @Test
    void 공백을_제거한_이름을_반환한다() {
        // given
        String rawName = " Player Name ";
        String cleanedName = "Player Name";
        // when
        Name name = new Name(rawName);
        // then
        assertThat(name.getValue()).isEqualTo(cleanedName);
    }

    @Test
    void 이름이_null인_경우_에러를_던진다() {
        assertThatThrownBy(() -> new Name(null))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이름이_blank인_경우_에러를_던진다() {
        assertThatThrownBy(() -> new Name(""))
            .isInstanceOf(IllegalArgumentException.class);
    }

}