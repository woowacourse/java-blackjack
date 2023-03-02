package blackjack.domain.user.name;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerNameTest {

    @Test
    void 이름은_항상_딜러다() {
        // given
        DealerName name = new DealerName();

        // then
        Assertions.assertThat(name.getName()).isEqualTo("딜러");
    }
}
