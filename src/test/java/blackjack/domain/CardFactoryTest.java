package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @DisplayName("만들어낸 Card 객체의 개수가 52개인지 확인한다.")
    @Test
    public void checkSize() {
        assertThat(CardFactory.make().size()).isEqualTo(52);
    }
}
