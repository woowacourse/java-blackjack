package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NameTest {
    @DisplayName("이름이 딜러인지 판단한다.")
    @Test
    void isDealer() {
        final Name name = new Name("딜러");

        assertThat(name.isDealerName()).isTrue();
    }
}
